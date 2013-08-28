package FileUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.com.bytecode.opencsv.CSVWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FolderUtil {
	private FileInputStream fis;
	private String dig;
	private java.util.List<String> childrens = new ArrayList();
	private static Logger log=LoggerFactory.getLogger(FolderUtil.class);

	public FolderUtil() {
	}

	public String genHash(String hashType, String filePath) {
		try {
			fis = new FileInputStream(new File(filePath));
			if (hashType.equals("md5"))
				dig = DigestUtils.md5Hex(fis);
			if (hashType.equals("sha1"))
				dig = DigestUtils.sha1Hex(fis);
		} catch (IOException e) {
			log.info("file not found");
		}

		return dig;
	}

	public String[] listFolders(String dirPath) {
		File dir = new File(dirPath);
		String[] children = dir.list();
		return children;
	}

	public java.util.List<String> listSubFolders(String dirPath) {
		File dir = new File(dirPath);
		for (File f : dir.listFiles()) {
			if (f.isDirectory())
				childrens = listSubFolders(f.getPath());
			else
				childrens.add(f.getName());
			;

		}

		return childrens;
	}

	public void exportToHTML(String texto, String path) {
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("src/templates/htmllate.ftl");

			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("directory", path);

			java.util.List<String> files = Arrays.asList(texto.split("\n"));

			data.put("Files", files);

			// Console output
			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();

			// File output
			Writer file = new FileWriter(new File("C:\\FTL_helloworld.html"));
			template.process(data, file);
			file.flush();
			file.close();

		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (TemplateException e) {
			log.error(e.getMessage());
		}

	}

	public void exportToTXT(String texto, String path) {
		Configuration cfg = new Configuration();
		try {
			// Load template from source folder
			Template template = cfg.getTemplate("src/templates/txtlate.ftl");

			// Build the data-model
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("directory", path);

			java.util.List<String> files = Arrays.asList(texto.split("\n"));

			data.put("Files", files);

			// Console output
			Writer out = new OutputStreamWriter(System.out);
			template.process(data, out);
			out.flush();

			// File output
			Writer file = new FileWriter(new File("C:\\FTL_helloworld.txt"));
			template.process(data, file);
			file.flush();
			file.close();

		} catch (IOException e) {
            log.error(e.getMessage());
		} catch (TemplateException e) {
			log.error(e.getMessage());
		}
	}

	public void exportToPDF(String texto) {
		Document document = new Document();
        String[] files=texto.split("\n");
        com.itextpdf.text.List list = new List(true,20);

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("C:\\filelist.pdf"));
            

            document.open();
            for(String s:files) {
            	list.add(new ListItem(s));
             
            	
            }
			document.add(list);   

            
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            log.error(e.getMessage());
        }

	}

	public void exportToCSV(String texto) {
		String csv = "C:\\filelist.csv";
		CSVWriter writer;
		try {
			writer = new CSVWriter(new FileWriter(csv));
			String [] files = texto.split("\n");

			writer.writeNext(files);

			writer.close();
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		

	}

	public void exportToXLS(String texto) {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Style example");
		String[] files=texto.split("\n");
		for(int i=0;i<files.length;i++) {
			Row row = sheet.createRow(i);
			Cell cell1 = row.createCell(0);
			Cell cell2 = row.createCell(1);
			cell1.setCellValue(i);
			cell2.setCellValue(files[i]);
		}
		
		try {
		    FileOutputStream out = new FileOutputStream(new File("C:\\style.xls"));
		    workbook.write(out);
		    out.close();
		     
		
		} catch (IOException e) {
		    log.error(e.getMessage());
		}
		
		
		

	}
}
