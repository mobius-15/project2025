package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;

import org.springframework.core.io.UrlResource;
import org.springframework.util.StreamUtils;

public class ResourceImpl implements Resource {
//	@Value("${resource.greeting:http://localhost:8080/myApp/greeting.json}")
	Resource greetingResource;
	public void accessResource()throws IOException {
		/*HTTP経由でWebリソースを取得*/
		Resource greetingResource=(Resource) new UrlResource("http://localhost:8080/myApp/greeting.json");
		
		try(InputStream in =greetingResource.getInputStream()){
			String content =StreamUtils.copyToString(in, StandardCharsets.UTF_8);
			System.out.println(content);
		}
	}
	@Override
	public InputStream getInputStream() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public boolean exists() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean isOpen() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean isFile() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public URL getURL() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public URI getURI() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public File getFile() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public ReadableByteChannel readableChannel() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public long contentLength() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	@Override
	public long lastModified() throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}
	@Override
	public Resource createRelative(String relativePath) throws IOException {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public String getFilename() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public String getDescription() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
