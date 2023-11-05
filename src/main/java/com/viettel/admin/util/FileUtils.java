package com.viettel.admin.util;

import com.viettel.admin.common.Const;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Component
public class FileUtils {

    public static org.apache.poi.ss.usermodel.Workbook getWorkBook(String filePath){
        String fileType = filePath.substring(filePath.length() - 4);
        org.apache.poi.ss.usermodel.Workbook workbook;
        try {
            if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(new FileInputStream(filePath));
                return workbook;
            } else if (fileType.equals(".xls")){
                workbook = new HSSFWorkbook(new FileInputStream(filePath));
                return workbook;
            }else{
                return null;
            }
        } catch (Exception e) {
            ExceptionUtils.showLogStackTrace(e);
            return null;
        }
    }

    public static org.apache.poi.ss.usermodel.Workbook getWorkBook(FileInputStream fileInputStream, String filePath){
        String fileType = filePath.substring(filePath.length() - 4);
        org.apache.poi.ss.usermodel.Workbook workbook;
        try {
            if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(fileInputStream);
                return workbook;
            } else if (fileType.equals(".xls")){
                workbook = new HSSFWorkbook(fileInputStream);
                return workbook;
            }else{
                return null;
            }
        } catch (Exception e) {
            ExceptionUtils.showLogStackTrace(e);
            return null;
        }
    }

    public static org.apache.poi.ss.usermodel.Workbook getWorkBook(MultipartFile file){
        if(file.getOriginalFilename() == null){
            return null;
        }
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().length() - 4);
        org.apache.poi.ss.usermodel.Workbook workbook;
        try {
            if (fileType.equals("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
                return workbook;
            } else if (fileType.equals(".xls")){
                workbook = new HSSFWorkbook(file.getInputStream());
                return workbook;
            }else{
                return null;
            }
        } catch (Exception e) {
            ExceptionUtils.showLogStackTrace(e);
            return null;
        }
    }

    public static String getCellStringValue(org.apache.poi.ss.usermodel.Cell cell){
        if(cell != null) {
            cell.setCellType(CellType.STRING);
            String content = cell.getStringCellValue();
            if (!DataUtil.isNullOrEmpty(content)){
                content = content.trim();
                content = content.replace("¥", "");
                if (content.substring(content.length() - 1).equals("₫") || content.substring(content.length() - 1).equals("đ")){
                    content = content.substring(0, content.length() - 1).trim();
                }
            }
            return content;
        }
        return null;
    }

    public static String getCellDateValue(org.apache.poi.ss.usermodel.Cell cell){
        if(cell != null){
            try {
                if (cell.getCellType() == CellType.NUMERIC){
                    return null;
                }
                if (cell.getCellType() == CellType.STRING){
                    String content = cell.getStringCellValue();
                    if (!DataUtil.isNullOrEmpty(content)){
                        try {
                            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(content);
                            if (date1 != null){
                                return content;
                            }
                        }catch (Exception e){

                        }
                    }
                    return null;
                }
                Date date = cell.getDateCellValue();
                if (date != null){
                    return new SimpleDateFormat("dd/MM/yyyy").format(date);
                }
            }catch (Exception e){
                return null;
            }
        }
        return null;
    }

    public static Integer getCellIntegerValue(org.apache.poi.ss.usermodel.Cell cell){
        if(cell != null){
            if(cell.getCellType() == CellType.STRING){
                String cellValue = cell.getStringCellValue().trim();
                if(cellValue.isEmpty()){
                    return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_INTEGER_VALUE;
                }else{
                    try {
                        return Integer.parseInt(cellValue);
                    }catch (Exception e){
                        return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_INTEGER_VALUE;
                    }
                }
            }
            if (cell.getCellType() == CellType.NUMERIC){
                double cellValue = cell.getNumericCellValue();
                return Math.toIntExact(Math.round(cellValue));
            }
            if (cell.getCellType() == CellType.FORMULA){
                String cellValue = cell.getRichStringCellValue().getString().trim();
                if (cellValue.isEmpty()){
                    return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_INTEGER_VALUE;
                }
                try {
                    return Integer.parseInt(cellValue);
                }catch (Exception e){
                    return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_INTEGER_VALUE;
                }
            }
        }
        return null;
    }

    public static Double getCellDoubleValue(org.apache.poi.ss.usermodel.Cell cell){
        if(cell != null){
            if(cell.getCellType() == CellType.STRING){
                String cellValue = cell.getStringCellValue().trim();
                if(cellValue.isEmpty()){
                    return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_DOUBLE_VALUE;
                }else{
                    try {
                        return Double.parseDouble(cellValue);
                    }catch (Exception e){
                        return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_DOUBLE_VALUE;
                    }
                }
            }
            if (cell.getCellType() == CellType.NUMERIC){
                return cell.getNumericCellValue();
            }
            if (cell.getCellType() == CellType.FORMULA){
                String cellValue = cell.getRichStringCellValue().getString().trim();
                if (cellValue.isEmpty()){
                    return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_DOUBLE_VALUE;
                }
                try {
                    return Double.parseDouble(cellValue);
                }catch (Exception e){
                    return Const.DEFAULT_VALUE_RESPONSE.DEFAULT_DOUBLE_VALUE;
                }
            }
        }
        return null;
    }

    public static void createCellContent(Row row, int cellIndex, String cellContent, CellStyle cellStyle){
        Cell cell0 = row.createCell(cellIndex);
        if (cellContent == null){
            cell0.setBlank();
        }else{
            cell0.setCellValue(cellContent);
        }
        cell0.setCellStyle(cellStyle);
    }

    public static void createCellContent(Row row, int cellIndex, Integer cellContent, CellStyle cellStyle){
        Cell cell0 = row.createCell(cellIndex);
        if (cellContent == null){
            cell0.setBlank();
        }else{
            cell0.setCellValue(cellContent);
        }
        cell0.setCellStyle(cellStyle);
    }

    public static void createCellContent(Row row, int cellIndex, Double cellContent, CellStyle cellStyle){
        Cell cell0 = row.createCell(cellIndex);
        if (cellContent == null){
            cell0.setBlank();
        }else{
            cell0.setCellValue(cellContent);
        }
        cell0.setCellStyle(cellStyle);
    }

    public static void createCellContent(Row row, int cellIndex, Long cellContent, CellStyle cellStyle){
        Cell cell0 = row.createCell(cellIndex);
        if (cellContent == null){
            cell0.setBlank();
        }else{
            cell0.setCellValue(cellContent);
        }
        cell0.setCellStyle(cellStyle);
    }

    public static void setCellStyleBorder(CellStyle cellStyle){
        cellStyle.setBorderBottom(BorderStyle.MEDIUM);
        cellStyle.setBorderTop(BorderStyle.MEDIUM);
        cellStyle.setBorderRight(BorderStyle.MEDIUM);
        cellStyle.setBorderLeft(BorderStyle.MEDIUM);
    }

    public static void setCellFill(CellStyle cellStyle, short fillColer){
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(fillColer);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//    cellStyle.setDataFormat((short)8);
    }

    public static boolean resizeImage(File input, String outputFilePath) {
        if (input.exists()) {
            try {
                BufferedImage image = ImageIO.read(input);
                BufferedImage resized = scale(image);
                File output = new File(outputFilePath);
                ImageIO.write(resized, "png", output);
                return true;
            } catch (IOException e) {
                throw new RuntimeException("Không tạo được ảnh thumbnail");
            }
        }
        throw new RuntimeException("File trống");
    }

    public static BufferedImage scale(BufferedImage source) {
        int w = (int) (source.getWidth() * 0.2);
        int h = (int) (source.getHeight() * 0.2);
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = combined.createGraphics();
        double xScale = (double) w / source.getWidth();
        double yScale = (double) h / source.getHeight();
        AffineTransform at = AffineTransform.getScaleInstance(xScale, yScale);
        g2d.drawRenderedImage(source, at);
        g2d.dispose();
        return combined;
    }

    public static Integer getMultipartFileSize(MultipartFile file) {
        return (int) file.getSize() / 1024;
    }

    public static String createBase64File(Workbook workbook){
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            String base64String = Base64Utils.encodeToString(baos.toByteArray());
            workbook.close();
            baos.close();
            return base64String;
        }catch (Exception e){
            ExceptionUtils.showLogStackTrace(e);
            throw new IllegalArgumentException(Const.MESSAGE_CODE.CAN_NOT_CREATE_EXCEL_FILE);
        }
    }

    public static CellStyle createBaseExcelFile(Workbook workbook, Sheet sheet, String[] titleList, String titleContent){
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 13);
        font.setFontName("Times New Roman");
        font.setColor(IndexedColors.BLACK.getIndex());

        Font fontNomal = workbook.createFont();
        fontNomal.setBold(false);
        fontNomal.setFontHeightInPoints((short) 13);
        fontNomal.setFontName("Times New Roman");
        fontNomal.setColor(IndexedColors.BLACK.getIndex());

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        FileUtils.setCellStyleBorder(cellStyle);
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFPalette palette = hwb.getCustomPalette();
        HSSFColor myColor = palette.findSimilarColor(255, 153, 0);
        FileUtils.setCellFill(cellStyle, myColor.getIndex());

        CellStyle styleNomal = workbook.createCellStyle();
        styleNomal.setFont(fontNomal);
        FileUtils.setCellStyleBorder(styleNomal);
        FileUtils.setCellFill(styleNomal, IndexedColors.WHITE.getIndex());

        Row titleContentRow = sheet.createRow(0);
        Cell cellTotal = titleContentRow.createCell(0);
        cellTotal.setCellValue(Const.VALUE_EXCEL.SPACE + titleContent);
        cellTotal.setCellStyle(styleNomal);

        Row titleRow = sheet.createRow(1);
        for (int j = 0; j < titleList.length; j++) {
            Cell cell = titleRow.createCell(j);
            cell.setCellValue(titleList[j]);
            cell.setCellStyle(cellStyle);
        }
        titleRow.setHeight((short)-1);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, titleList.length - 1));
        return styleNomal;
    }

    public static void copyRow(Workbook workbook, Sheet worksheet, int sourceRowNum, int destinationRowNum) {
        // Get the source / new row
        Row newRow = worksheet.getRow(destinationRowNum);
        Row sourceRow = worksheet.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow != null) {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            Cell oldCell = sourceRow.getCell(i);
            Cell newCell = newRow.createCell(i);

            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }

            // Copy style from old cell and apply to new cell
            CellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());

            // Set the cell data value
            switch (oldCell.getCellType()) {
                case BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }
        }

        // If there are are any merged regions in the source row, copy to new row
        for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(),
                        (newRow.getRowNum() +
                                (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow()
                                )),
                        cellRangeAddress.getFirstColumn(),
                        cellRangeAddress.getLastColumn());
                worksheet.addMergedRegion(newCellRangeAddress);
            }
        }
    }

    public static void removeRow(Sheet sheet, int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        sheet.removeRow(row);
        sheet.shiftRows(rowIndex + 1, 459, -1);
    }

    public static void addRow(Sheet sheet, Row rowSource, int rowIndex){
        sheet.shiftRows(rowIndex, sheet.getLastRowNum() + 1, 1);
        sheet.createRow(rowIndex);
        Row rowNew = sheet.getRow(rowIndex);
        if (rowNew != null){
            rowNew.setRowStyle(rowSource.getRowStyle());
            for (int c=0;c<rowSource.getLastCellNum();c++){
                Cell sourceCell = rowSource.getCell(c);
                if (sourceCell != null){
                    Cell newCell = rowNew.getCell(c);
                    if (newCell == null){
                        newCell = rowNew.createCell(c);
                    }
                    newCell.setCellStyle(sourceCell.getCellStyle());
                }
            }
        }
    }
//
//    public static List<Picture> getListPictureOfSheet(Sheet sheet){
//        Drawing<?> draw = sheet.createDrawingPatriarch();
//        List<Picture> pics = new ArrayList<>();
//        if (draw instanceof HSSFPatriarch) {
//            HSSFPatriarch hp = (HSSFPatriarch)draw;
//            for (HSSFShape hs : hp.getChildren()) {
//                if (hs instanceof Picture)  {
//                    pics.add((Picture)hs);
//                }
//            }
//        } else {
//            XSSFDrawing xdraw = (XSSFDrawing)draw;
//            for (XSSFShape xs : xdraw.getShapes()) {
//                if (xs instanceof Picture) {
//                    pics.add((Picture)xs);
//                }
//            }
//        }
//        return pics;
//    }


    public static void main(String[] args) throws Exception {

        //Workbook workbook = new HSSFWorkbook(); String filePath = "./Excel.xls";
        Workbook workbook = new XSSFWorkbook(); String filePath = "./Excel.xlsx";

        Sheet sheet = workbook.createSheet();
        Row row = null;

        //create cell style horizontal alignment - center, vertical alignment - center, wrap text
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);

        //insert picture's media into workbook
        InputStream inputStream = new FileInputStream("./logo.png");
        byte[] imageBytes = IOUtils.toByteArray(inputStream);
        int pictureureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
        inputStream.close();

        //insert picture anchored over the cells of the sheet
        CreationHelper helper = workbook.getCreationHelper();
        Drawing drawing = sheet.createDrawingPatriarch();
        ClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(0); //col A
        anchor.setRow1(0); //row 1
        Picture pict = drawing.createPicture(anchor, pictureureIdx);
        pict.resize(); //now picture is anchored at A1 and sized to it's original size

        //get picture's original size
        int pictOriginalWidthInPixels = pict.getImageDimension().width;
        int pictOriginalHeightInPixels = pict.getImageDimension().height;

        //get height of row 1 to 4
        float rowHeightInPixels = 0f;
        for (int r = 0; r < 4; r++) {
            row = sheet.getRow(r); if (row == null) row = sheet.createRow(r);
            float rowHeightInPoints = row.getHeightInPoints();
            rowHeightInPixels += rowHeightInPoints * Units.PIXEL_DPI / Units.POINT_DPI;
        }
        //we want scaling in aspect ratio
        float scale = rowHeightInPixels / pictOriginalHeightInPixels;
        pict.resize(scale, scale); //now picture is resized to fit into the first 4 rows

        //create merged cells for heading
        sheet.addMergedRegion(new CellRangeAddress(0,3,0,7)); //merged region A1:H4

        //set text for merged region in A1
        row = sheet.getRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Golden Heights, 9/1, sector 3, Huda Techno Enclave,\n"
                +"Madhapur (HITEC city), Hyderabad, Telangana - 500 081, India.\n"
                +"Phone: 91 40.23116868 Email: info@sysintelli.com");
        cell.setCellStyle(cellStyle);

        //set column widths
        for (int c = 0; c < 8; c++) {
            sheet.setColumnWidth(c, 15*256); //column width 15 default character widths
        }

        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);
        out.close();
        workbook.close();

    }
}
