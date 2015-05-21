package br.com.mystudies.javaee.concurrency.api;


public interface ExportFile  {


	default FileExportResult export(ExportFilter exportFilter) {
		return null;
	}

}
