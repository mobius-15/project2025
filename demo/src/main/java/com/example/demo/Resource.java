package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

import org.springframework.core.io.InputStreamSource;

public interface Resource extends InputStreamSource {
	boolean exists();	/*リソースの存在判定*/

	boolean isOpen();	/*リソースを読み込むストリームのオープン*/

	boolean isFile();

	URL getURL() throws IOException;

	URI getURI() throws IOException;

	File getFile() throws IOException;

	ReadableByteChannel readableChannel() throws IOException;

	long contentLength() throws IOException;

	long lastModified() throws IOException;

	Resource createRelative(String relativePath) throws IOException;

	String getFilename();

	String getDescription();

}
