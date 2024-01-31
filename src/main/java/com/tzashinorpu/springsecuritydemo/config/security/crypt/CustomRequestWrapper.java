package com.tzashinorpu.springsecuritydemo.config.security.crypt;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class CustomRequestWrapper extends HttpServletRequestWrapper {
	private final String decryptBody;

	public CustomRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			String encryptBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
			this.decryptBody = CrypticUtil.requestDecrypt(encryptBody);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// 因为在构造函数中使用了 getInputStream ，因此重写 getInputStream 方法防止后续读不到数据
	@Override
	public ServletInputStream getInputStream() {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(this.decryptBody.getBytes());
		return new CustomServletInputStream(inputStream);
	}

	static class CustomServletInputStream extends ServletInputStream {
		private final InputStream sourceStream;

		private boolean finished = false;

		public CustomServletInputStream(InputStream sourceStream) {
			Assert.notNull(sourceStream, "Source InputStream must not be null");
			this.sourceStream = sourceStream;
		}


		public final InputStream getSourceStream() {
			return this.sourceStream;
		}


		@Override
		public int read() throws IOException {
			int data = this.sourceStream.read();
			if (data == -1) this.finished = true;
			return data;
		}

		@Override
		public int available() throws IOException {
			return this.sourceStream.available();
		}

		@Override
		public void close() throws IOException {
			super.close();
			this.sourceStream.close();
		}

		@Override
		public boolean isFinished() {
			return this.finished;
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener readListener) {
			throw new UnsupportedOperationException();
		}
	}
}
