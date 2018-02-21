package org.apache.log4j;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CleanDailyRollingFileAppender extends DailyRollingFileAppender {

	private static Logger logger = LoggerFactory.getLogger(CleanDailyRollingFileAppender.class);

	public CleanDailyRollingFileAppender() {
	}

	// 日志文件保留份数
	private int maxbackupindex = 90;

	public int getMaxbackupindex() {
		return maxbackupindex;
	}

	public void setMaxbackupindex(int maxbackupindex) {
		this.maxbackupindex = maxbackupindex;
	}

	public CleanDailyRollingFileAppender(Layout layout, String filename, String datePattern, int maxbackupindex)
			throws IOException {
		super(layout, filename, datePattern);
		this.maxbackupindex = maxbackupindex;
	}

	public void rollOver() throws IOException {
		super.rollOver();
		logger.debug("保留文件数量" + maxbackupindex + "，日志文件名称为：" + fileName);
		List<File> fileList = getAllLogs();
		sortFiles(fileList);
		logger.debug(fileList.toString());
		deleteOvermuch(fileList);
	}

	private List<File> getAllLogs() {
		final File file = new File(fileName);
		File logPath = file.getParentFile();
		if (logPath == null) {
			logPath = new File(".");
		}

		File files[] = logPath.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if (pathname.getName().startsWith(fileName)) {
					return true;
				}
				return false;
			}
		});
		return Arrays.asList(files);
	}

	/**
	 * 根据文件名称上的特定格式的时间排序日志文件
	 * 
	 * @param fileList
	 */
	private void sortFiles(List<File> fileList) {
		Collections.sort(fileList, new Comparator<File>() {
			public int compare(File f1, File f2) {
				try {
					if (getDateStr(f1).isEmpty()) {
						return 1;
					}

					Date date1 = sdf.parse(getDateStr(f1));
					if (getDateStr(f2).isEmpty()) {
						return -1;
					}

					Date date2 = sdf.parse(getDateStr(f2));
					if (date1.getTime() > date2.getTime()) {
						return 1;
					} else if (date1.getTime() < date2.getTime()) {
						return -1;
					}
				} catch (ParseException e) {
					logger.error("", e);
				}
				return 0;
			}
		});
	}

	private String getDateStr(File file) {
		return file.getName().replaceFirst(fileName, "");
	}

	/**
	 * 删除过多的文件
	 * 
	 * @param fileList
	 *            所有日志文件
	 */
	private void deleteOvermuch(List<File> fileList) {
		if (fileList.size() > maxbackupindex) {
			for (int i = 0; i < fileList.size() - maxbackupindex; i++) {
				fileList.get(i).delete();
				logger.debug("删除日志" + fileList.get(i));
			}
		}
	}

}
