package Excel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import GUI.Model.Model;
import Main.AssignmentObject;
import Main.ObjectList;
import Main.ScoreType;

public class SpreadSheetReader {
	private File file;
	private Model model;
	private ObjectList list1;
	private ObjectList list2;
	private Map<String,Double> objectiveValues;

	public SpreadSheetReader(File file, Model model) { 
		this.file = file;
		this.model = model;
	}

	public List<String> loadData() throws Exception	{
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet sheet = workBook.getSheetAt(0);
		XSSFRow firstRow = sheet.getRow(0);
		
		list1 = new ObjectList(model.getList1Name());
		list2 = new ObjectList(model.getList2Name());
		objectiveValues = new HashMap<String, Double>();
		List<String> errors = new ArrayList<String>();
		for (int i = 0; i < sheet.getPhysicalNumberOfRows(); i++) {
			XSSFRow row = sheet.getRow(i);
			Cell first = row.getCell(0);
			Cell second = row.getCell(1);
			Cell third = row.getCell(2);
			Set<Double> set = new HashSet<Double>();
			if (first.getCellType() == Cell.CELL_TYPE_STRING 
					&& second.getCellType() == Cell.CELL_TYPE_STRING
					&& third.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				String objectName1 = first.getStringCellValue();
				String objectName2 = second.getStringCellValue();
				AssignmentObject o1 = new AssignmentObject(objectName1);
				AssignmentObject o2 = new AssignmentObject(objectName2);
				if (!list1.contains(o1)) list1.add(o1);
				if (!list2.contains(o2)) list2.add(o2);
				double value = third.getNumericCellValue();
				if (model.getScoreRestriction().getType().equals(ScoreType.POSITIVE)) {
					if (value < 0) {
						value = 0;
						errors.add(objectName1+objectName2);
					}
				} else if (model.getScoreRestriction().getType().equals(ScoreType.NEGATIVE)) {
					if (value >= 0) {
						value = 0;
						errors.add(objectName1+objectName2);
					}
				}
				objectiveValues.put(o1.getName()+o2.getName(), value);
				set.add(value);
			} else {
				throw new Exception("Error Loading File. Please Check the Data Format.");
			}
		}
		return errors;
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
