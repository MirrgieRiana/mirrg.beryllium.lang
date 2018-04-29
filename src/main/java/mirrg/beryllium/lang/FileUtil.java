package mirrg.beryllium.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public interface FileUtil
{

	public static FileOutputStream getOutputStreamAndMkdirs(File file) throws FileNotFoundException
	{
		file.getAbsoluteFile().getParentFile().mkdirs();
		return new FileOutputStream(file);
	}

}
