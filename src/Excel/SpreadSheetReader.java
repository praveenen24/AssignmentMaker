package Excel;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import GUI.Model.Model;
import Main.AssignmentObject;
import Main.ObjectList;

public class SpreadSheetReader {
	private File file;
	private ObjectList list1;
	private ObjectList list2;
	private Map<String,Double> objectiveValues;

	public SpreadSheetReader(File file) { 
		this.file = file;
	}

	public void readData() throws Exception {
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);
		
		//First Row
		Iterator<Cell> iterator = firstRow.cellIterator();
		list2 = new ObjectList("List2");
		list1 = new ObjectList("List1");
		objectiveValues = new HashMap<String, Double>();
		while(iterator.hasNext()) {
			Cell cell = iterator.next();
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				String val = cell.getStringCellValue();
				if (val.trim().length() > 0) {
					list2.add(new AssignmentObject(val));
				}
			}
		}
		
		// Data
		for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			Cell firstCell = row.getCell(0);
			String object1 = "";
			if (firstCell.getCellType() == Cell.CELL_TYPE_STRING) {
				object1 = firstCell.getStringCellValue();
				if (object1.trim().length() > 0) {
					list1.add(new AssignmentObject(object1));
				}
			}
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell value = cellIterator.next();
				if (value.getColumnIndex() == 0) continue;
				Cell o2Name = firstRow.getCell(value.getColumnIndex());
				String object2 = o2Name.getStringCellValue();
				if (value.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					objectiveValues.put(object1+object2, value.getNumericCellValue());
				} else if (value.getCellType() == Cell.CELL_TYPE_STRING) {
					objectiveValues.put(object1+object2, Double.parseDouble(value.getStringCellValue()));
				}
			}
		}
	}
	
	public ObjectList getList1() {
		return list1;
	}
	
	public ObjectList getList2() {
		return list2;
	}
	
	public Map<String, Double> getObjectiveValues() {
		return objectiveValues;
	}
	
	public static void main(String[] args) {
		
	}
}
