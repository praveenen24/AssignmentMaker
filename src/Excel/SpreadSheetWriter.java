package Excel;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import GUI.Model.Model;
import Main.AssignmentObject;

public class SpreadSheetWriter {
	private File file;
	private Model model;

	public SpreadSheetWriter(File file, Model model) {
		this.file = file;
		this.model = model;
	}

	public void save() {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet s = workBook.createSheet("Assignment Data");
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		int rowNumber = 1;

		for (AssignmentObject o1 : model.getObjectList1()) {
			for (AssignmentObject o2 : model.getObjectList2()) {
				Object[] row = new Object[3];
				row[0] = o1.toString();
				row[1] = o2.toString();
				row[2] = model.getObjectiveValue(o1.getName()+o2.getName());
				data.put(Integer.toString(rowNumber), row);
				rowNumber++;
			}	
		}

		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset)
		{
			Row row = s.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(file.getAbsolutePath() + ".xlsx"));
			workBook.write(out);
			out.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

	}

	public void saveData() {
		XSSFWorkbook workBook = new XSSFWorkbook();
		XSSFSheet s = workBook.createSheet("Assignment Data");
		Map<String, Object[]> data = new TreeMap<String, Object[]>();
		int cols = model.getObjectList2().size()+1;
		Object[] first = new Object[cols];
		first[0] = " ";
		int index = 1;
		int iterator = 1;
		for (AssignmentObject o : model.getObjectList2()) {
			first[index] = o.toString();
			index++;
		}
		data.put(Integer.toString(iterator), first);
		iterator++;
		for (AssignmentObject o1 : model.getObjectList1()) {
			Object[] row = new Object[cols];
			index = 1;
			row[0] = o1.toString();
			for (AssignmentObject o2 : model.getObjectList2()) {
				String key = o1.getName()+o2.getName();
				row[index] = model.getObjectiveValue(key);
				index++;
			}
			data.put(Integer.toString(iterator), row);
			iterator++;
		}

		//Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset)
		{
			Row row = s.createRow(rownum++);
			Object [] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr)
			{
				Cell cell = row.createCell(cellnum++);
				if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}
		}
		try
		{
			//Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(file.getAbsolutePath() + ".xlsx"));
			workBook.write(out);
			out.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
}
