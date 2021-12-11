package com.projeto.tccv1.service;

import java.util.Date;

public interface RelatorioAgendaService {
	public byte[] gerarRelatorio(Long idPaciente,Long idProfissional,Date dataIncio, Date dataFim);
}
