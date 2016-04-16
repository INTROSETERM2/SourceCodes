package Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.UIManager;

public class Picturemover {

	public Picturemover(String path) {

		 File from = new File(path);

	 File to = new File("C:\\School\\INTROSE\\INTROSE Github\\SourceCodes\\INTROSE\\Pictures\\" + from.getName());
	
	 String p = to.getAbsolutePath().toString();
		int nCopy = 0;
		String d = "";
		String ex = "";

		try {
			while (to.exists()) {
				nCopy++;
				for (int i = p.length() - 1; i >= 0; i--) {
					if (p.charAt(i) == '.') {
						if (nCopy == 1) {
							d = p.substring(0, i);
							ex = p.substring(i);
							p = p.substring(0, i) + " - Copy" + p.substring(i);
						} else {
							p = d + " - Copy (" + nCopy + ")" + ex;
						}
						to = new File(p);
					}
				}
			}
			Files.copy(from.toPath(), to.toPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		deleteFile(from);
	}

	public void deleteFile(File from) {
		try {
			Files.delete(from.toPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}

//// ORIGINAL COPY PASTE FROM PAT
// private static void copyFile(File from, File to) {
// int nCopy = 0;
// String p = to.getAbsolutePath().toString();
// String d = "";
// String ex = "";
//
// try {
// while (to.exists()) {
// nCopy++;
// for (int i = p.length() - 1; i >= 0; i--) {
// if (p.charAt(i) == '.') {
// if (nCopy == 1) {
// d = p.substring(0, i);
// ex = p.substring(i);
// p = p.substring(0, i) + " - Copy" + p.substring(i);
// } else {
// p = d + " - Copy (" + nCopy + ")" + ex;
// }
// to = new File(p);
// }
// }
// }
// Files.copy(from.toPath(), to.toPath());
// } catch (IOException e) {
// System.out.println(e.getMessage());
// }
// }
//
// private static void deleteFile(File from) {
// try {
// Files.delete(from.toPath());
// } catch (IOException e) {
// System.out.println(e.getMessage());
// }
// }
//
// private static void moveFile(File from, File to) {
// copyFile(from, to);
// deleteFile(from);
// }