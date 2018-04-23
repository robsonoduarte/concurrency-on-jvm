package br.com.mystudies.javaee.concurrency.api;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import javax.enterprise.concurrent.ManagedExecutorService;

public class ManagedExecutorFileExport {

	private ManagedExecutorService managedExecutorService;
	private FactoryExportFile factoryExportFile;



	public void execute(ExportFileSchedulle exportFileSchedulle) {

		List<ExportFile> exports  = new ArrayList<>();

		asList("type1", "type2")
			.stream()
			.map(t -> factoryExportFile.create(t))
			.forEach(ef -> exports.add(ef));

		List<Callable<FileExportResult>> result = new ArrayList<>();

		exports.stream()
			.map(ex -> callable(ex,exportFileSchedulle.exportFilter()))
			.forEach(c -> result.add(c));

		try {

			List<Future<FileExportResult>> all = managedExecutorService.invokeAll(result);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		/*
		List<Callable<FileExportResult>> list = new ArrayList<>();


		for (FileExport fileExport : filesExports) {
			list.add(e)
		}*/


/*		filesExports
			.stream()
			.forEach(fx -> temp(fx));*/
	}






	private Callable<FileExportResult> callable(final ExportFile ex, final ExportFilter exportFilter) {
		return new Callable<FileExportResult>() {

			@Override
			public FileExportResult call() throws Exception {
				return ex.export(exportFilter);
			}
		};
	}





}
