package org.eclipse.rap.rms.data;

import java.io.*;

public interface IStorageManager {
	void save( OutputStream out ) throws IOException;
	void load( InputStream in )throws IOException;
}
