package com.revenco.monthreportimport.ui.swing;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.revenco.monthreportimport.manager.YxbbYkbztjbExcelManager;
import com.revenco.monthreportimport.manager.YxbbYkbztjbManager;
import com.revenco.monthreportimport.manager.YxbbYkbztjbSqlFileManager;
import com.revenco.monthreportimport.util.ResourceBundleUtil;

public class MonthReportImportPanel extends JPanel implements ActionListener,
		PropertyChangeListener {

	private JButton openButton, saveButton, execButton;
	private JTextField openField, saveField;
	private JLabel openLabel, saveLabel;
	private JFileChooser openFileChooser, saveFileChooser;
	private File[] openFiles;
	private File saveFile;
	private Task task;

	public MonthReportImportPanel() {
		super(new BorderLayout());

		openButton = new JButton(ResourceBundleUtil.getDefaultBundle()
				.getString("select_label"));
		saveButton = new JButton(ResourceBundleUtil.getDefaultBundle()
				.getString("select_label"));
		execButton = new JButton(ResourceBundleUtil.getDefaultBundle()
				.getString("exec_label"));

		openField = new JTextField(20);
		saveField = new JTextField(20);
		openField.setEditable(false);
		saveField.setEditable(false);

		openLabel = new JLabel(ResourceBundleUtil.getDefaultBundle().getString(
				"import_path"));
		saveLabel = new JLabel(ResourceBundleUtil.getDefaultBundle().getString(
				"save_path"));

		openFileChooser = new JFileChooser();
		saveFileChooser = new JFileChooser();

		FileNameExtensionFilter openFilter = new FileNameExtensionFilter(
				"Excel", "xls", "xlsx");
		openFileChooser.setFileFilter(openFilter);
		openFileChooser.setMultiSelectionEnabled(true);

		JPanel fileChooserOpenPanel = new JPanel();
		JPanel fileChooserSavePanel = new JPanel();
		JPanel fileChooserPanel = new JPanel(new GridLayout(2, 1));

		fileChooserOpenPanel.add(openLabel);
		fileChooserOpenPanel.add(openField);
		fileChooserOpenPanel.add(openButton);

		fileChooserSavePanel.add(saveLabel);
		fileChooserSavePanel.add(saveField);
		fileChooserSavePanel.add(saveButton);

		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		execButton.addActionListener(this);

		fileChooserPanel.add(fileChooserOpenPanel);
		fileChooserPanel.add(fileChooserSavePanel);

		add(fileChooserPanel);
		add(execButton, BorderLayout.PAGE_END);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int returnVal = openFileChooser
					.showOpenDialog(MonthReportImportPanel.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				openFiles = openFileChooser.getSelectedFiles();
				String paths = "";
				int fileNum = openFiles.length;
				if (fileNum == 1) {
					paths = openFiles[0].getPath();
				} else {
					for (File file : openFiles) {
						if ("".endsWith(paths))
							paths += "\"" + file.getPath() + "\"";
						else
							paths += " " + "\"" + file.getPath() + "\"";
					}
				}

				openField.setText(paths);
			}
		} else if (e.getSource() == saveButton) {
			if (null != openFiles && openFiles.length > 0) {
				saveFileChooser.setCurrentDirectory(openFiles[0]);
			}

			int returnVal = saveFileChooser
					.showSaveDialog(MonthReportImportPanel.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				saveFile = saveFileChooser.getSelectedFile();
				saveField.setText(saveFile.getPath());
			}
		} else if (e.getSource() == execButton) {
			String openPath = openField.getText();
			String savePath = saveField.getText();
			if ("".endsWith(openPath) || "".endsWith(savePath)
					|| null == openPath || null == savePath) {
				JOptionPane.showMessageDialog(
						MonthReportImportPanel.this,
						ResourceBundleUtil.getDefaultBundle().getString(
								"path_is_null_warn"), ResourceBundleUtil
								.getDefaultBundle().getString("warning"),
						JOptionPane.WARNING_MESSAGE);
			} else {
				openButton.setEnabled(false);
				saveButton.setEnabled(false);
				execButton.setEnabled(false);
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				task = new Task();
				task.addPropertyChangeListener(this);
				task.execute();
			}
		}
	}

	class Task extends SwingWorker<Void, Void> {

		@Override
		protected Void doInBackground() throws Exception {
			try {
				YxbbYkbztjbManager mgr = new YxbbYkbztjbManager();
				YxbbYkbztjbExcelManager excelmgr = new YxbbYkbztjbExcelManager();
				YxbbYkbztjbSqlFileManager sqlmgr = new YxbbYkbztjbSqlFileManager();
				String content = "";
				for (File file : openFiles) { //
					content += mgr.getYxbbYkbztjbAsSqls(excelmgr
							.getOneWorkbookYxbbYkbztjb(file.getPath()));

				}

				sqlmgr.storeYxbbYkbztjbAsSqlFile(saveFile.getPath(), content);
				JOptionPane.showMessageDialog(
						MonthReportImportPanel.this,
						ResourceBundleUtil.getDefaultBundle().getString(
								"success")
								+ saveFile.getPath(), ResourceBundleUtil
								.getDefaultBundle().getString("complete"),
						JOptionPane.PLAIN_MESSAGE);

			} catch (FileAlreadyExistsException fex) {
				JOptionPane.showMessageDialog(
						MonthReportImportPanel.this,
						ResourceBundleUtil.getDefaultBundle().getString(
								"file_exists"), ResourceBundleUtil
								.getDefaultBundle().getString("error"),
						JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(
						MonthReportImportPanel.this,
						ResourceBundleUtil.getDefaultBundle().getString(
								"save_file_err"), ResourceBundleUtil
								.getDefaultBundle().getString("error"),
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(MonthReportImportPanel.this, e
						.getMessage(), ResourceBundleUtil.getDefaultBundle()
						.getString("error"), JOptionPane.ERROR_MESSAGE);
			}
			return null;
		}

		@Override
		protected void done() {
			setCursor(null);
			openButton.setEnabled(true);
			saveButton.setEnabled(true);
			execButton.setEnabled(true);
		}

	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}

}
