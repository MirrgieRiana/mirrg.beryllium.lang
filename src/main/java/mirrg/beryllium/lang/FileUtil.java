package mirrg.beryllium.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.stream.Collectors;

public interface FileUtil
{

	public static FileOutputStream getOutputStreamAndMkdirs(File file) throws FileNotFoundException
	{
		file.getAbsoluteFile().getParentFile().mkdirs();
		return new FileOutputStream(file);
	}

	public static ArrayList<Path> getFiles(Path dir) throws IOException
	{
		ArrayList<Path> pathes = new ArrayList<>();

		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException
			{
				pathes.add(path);
				return FileVisitResult.CONTINUE;
			}
		});

		return pathes;
	}

	public static ArrayList<File> getFiles(File dir) throws IOException
	{
		return getFiles(dir.toPath()).stream()
			.map(p -> p.toFile())
			.collect(Collectors.toCollection(ArrayList::new));
	}

}
