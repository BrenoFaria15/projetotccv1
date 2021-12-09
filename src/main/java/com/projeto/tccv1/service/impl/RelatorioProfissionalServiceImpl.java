package com.projeto.tccv1.service.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.projeto.tccv1.service.RelatorioProfissionalService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class RelatorioProfissionalServiceImpl implements RelatorioProfissionalService {

	@Value("classpath:Reports/profissionais-listagem.jrxml")
	private Resource relatorioProfissional;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public byte[] gerarRelatorio() {
		try(
				Connection conection = dataSource.getConnection();
				
				){
			JasperReport compiledReport = JasperCompileManager.compileReport(relatorioProfissional.getInputStream());
			
			Map<String,Object>parametros = new HashMap<>();
			
			JasperPrint print =  JasperFillManager.fillReport(compiledReport, parametros ,conection);
			
			return JasperExportManager.exportReportToPdf(print);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (JRException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
