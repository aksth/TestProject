package com.test.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class EmbeddedFonts {
	public static void main(String[] args) throws JRException, IOException {
		// No need to mention that you should precompile the reports
		try(InputStream jasper = EmbeddedFonts.class.getResourceAsStream("/reports/report.jrxml")) {
			JasperReport report = JasperCompileManager.compileReport(jasper);
			
			// JasperFillManager does not support immutable maps :(
			JasperPrint print = JasperFillManager.fillReport(report, new HashMap<String, Object>(), new JREmptyDataSource());
			JasperViewer.viewReport(print, false);
		}
	}
}
