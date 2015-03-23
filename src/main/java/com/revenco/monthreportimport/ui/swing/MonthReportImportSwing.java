package com.revenco.monthreportimport.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.revenco.monthreportimport.util.ResourceBundleUtil;

public class MonthReportImportSwing {
	public void createAndShowGUI() {
		JFrame frame = new JFrame(ResourceBundleUtil.getDefaultBundle()
				.getString("title"));
		JMenuBar menuBar = new JMenuBar();
		JMenu helpMenu = new JMenu(ResourceBundleUtil.getDefaultBundle()
				.getString("help_menu"));
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem helpMenuItem = new JMenuItem(ResourceBundleUtil
				.getDefaultBundle().getString("help_item"));
		JMenuItem aboutMenuItem = new JMenuItem(ResourceBundleUtil
				.getDefaultBundle().getString("about_item"));
		aboutMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						null,
						ResourceBundleUtil.getDefaultBundle().getString(
								"about_item_content"),
						ResourceBundleUtil.getDefaultBundle().getString(
								"about_item_title"), JOptionPane.PLAIN_MESSAGE);

			}
		});
		helpMenu.add(helpMenuItem);
		helpMenu.add(aboutMenuItem);
		menuBar.add(helpMenu);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new MonthReportImportPanel());
		frame.setJMenuBar(menuBar);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
