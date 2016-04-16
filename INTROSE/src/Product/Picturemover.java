package Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Picturemover {
	public Picturemover(File from, File to) {
		int nCopy = 0;
		String p = to.getAbsolutePath().toString();
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
	}

	private static void deleteFile(File from) {
		try {
			Files.delete(from.toPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void moveFile(File from, File to) {
		copyFile(from, to);
		deleteFile(from);
	}
}

////ORIGINAL COPY PASTE FROM PAT
//private static void copyFile(File from, File to) {
//	int nCopy = 0;
//	String p = to.getAbsolutePath().toString();
//	String d = "";
//	String ex = "";
//
//	try {
//		while (to.exists()) {
//			nCopy++;
//			for (int i = p.length() - 1; i >= 0; i--) {
//				if (p.charAt(i) == '.') {
//					if (nCopy == 1) {
//						d = p.substring(0, i);
//						ex = p.substring(i);
//						p = p.substring(0, i) + " - Copy" + p.substring(i);
//					} else {
//						p = d + " - Copy (" + nCopy + ")" + ex;
//					}
//					to = new File(p);
//				}
//			}
//		}
//		Files.copy(from.toPath(), to.toPath());
//	} catch (IOException e) {
//		System.out.println(e.getMessage());
//	}
//}
//
//private static void deleteFile(File from) {
//	try {
//		Files.delete(from.toPath());
//	} catch (IOException e) {
//		System.out.println(e.getMessage());
//	}
//}
//
//private static void moveFile(File from, File to) {
//	copyFile(from, to);
//	deleteFile(from);
//}