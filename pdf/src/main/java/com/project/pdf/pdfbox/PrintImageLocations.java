package com.project.pdf.pdfbox;

/**
 * Created by goforit on 2017/9/10.
 */

import com.project.common.sort.MaxHeap;
import org.apache.pdfbox.contentstream.PDFStreamEngine;
import org.apache.pdfbox.contentstream.operator.DrawObject;
import org.apache.pdfbox.contentstream.operator.Operator;
import org.apache.pdfbox.contentstream.operator.state.*;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.graphics.PDPostScriptXObject;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.util.Matrix;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This is an example on how to get the x/y coordinates of image locations.
 *
 * @author Ben Litchfield
 */
public class PrintImageLocations extends PDFStreamEngine
{
    private static final float SPACE_UNITS_TO_PIXELS= 4.34f;
    private MaxHeap<PdfImgLocation> pdfImgLocationMaxHeap = new MaxHeap<>();
    /**
     * Default constructor.
     *
     * @throws IOException If there is an error loading text stripper properties.
     */
    public PrintImageLocations() throws IOException
    {
        addOperator(new Concatenate());
        addOperator(new DrawObject());
        addOperator(new SetGraphicsStateParameters());
        addOperator(new Save());
        addOperator(new Restore());
        addOperator(new SetMatrix());
    }

    /**
     * This will print the documents data.
     *
     * @param args The command line arguments.
     *
     * @throws IOException If there is an error parsing the document.
     */
//    public static void main( String[] args ) throws IOException
//    {
//        args = new String[]{"/Users/apple/Downloads/13--带有下标[第8页].pdf"};
//        if( args.length != 1 )
//        {
//            usage();
//        }
//        else
//        {
//            try (PDDocument document = PDDocument.load(new File(args[0])))
//            {
//                PrintImageLocations printer = new PrintImageLocations();
//                int pageNum = 0;
//                for( PDPage page : document.getPages() )
//                {
//                    pageNum++;
//                    System.out.println( "Processing page: " + pageNum );
//                    printer.processPage(page);
//                }
//            }
//        }
//    }

    /**
     * This is used to handle an operation.
     *
     * @param operator The operation to perform.
     * @param operands The list of arguments.
     *
     * @throws IOException If there is an error processing the operation.
     */
    @Override
    protected void processOperator( Operator operator, List<COSBase> operands) throws IOException
    {
        String operation = operator.getName();
        if( "Do".equals(operation) )
        {
            COSName objectName = (COSName) operands.get( 0 );
            PDXObject xobject = getResources().getXObject( objectName );

            if( xobject instanceof PDPostScriptXObject)
            {
                PDPostScriptXObject image = (PDPostScriptXObject)xobject;

//                int imageWidth = image.getWidth();
//                int imageHeight = image.getHeight();
                System.out.println("*******************************************************************");
                System.out.println("Found image [" + objectName.getName() + "]");

                Matrix ctmNew = getGraphicsState().getCurrentTransformationMatrix();
                float imageXScale = ctmNew.getScalingFactorX();
                float imageYScale = ctmNew.getScalingFactorY();

                // position in user space units. 1 unit = 1/72 inch at 72 dpi
                float pixelY = ctmNew.getTranslateY();
                System.out.println("position in PDF = " + ctmNew.getTranslateX() + ", " + pixelY + " in user space units");
                // r aw size in pixels
//                System.out.println("raw image size  = " + imageWidth + ", " + imageHeight + " in pixels");

                pdfImgLocationMaxHeap.add(new PdfImgLocation(pixelY,imageYScale));

                // displayed size in user space units
                System.out.println("displayed size  = " + imageXScale + ", " + imageYScale + " in user space units");
                // displayed size in inches at 72 dpi rendering
                imageXScale /= 72;
                imageYScale /= 72;
                System.out.println("displayed size  = " + imageXScale + ", " + imageYScale + " in inches at 72 dpi rendering");
                // displayed size in millimeters at 72 dpi rendering
                imageXScale *= 25.4;
                imageYScale *= 25.4;
                System.out.println("displayed size  = " + imageXScale + ", " + imageYScale + " in millimeters at 72 dpi rendering");
                System.out.println();
            }
            else if(xobject instanceof PDFormXObject)
            {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else
        {
            super.processOperator( operator, operands);
        }
    }

    /**
     * This will print the usage for this document.
     */
    private static void usage()
    {
        System.err.println( "Usage: java " + PrintImageLocations.class.getName() + " <input-pdf>" );
    }

    public MaxHeap<PdfImgLocation> getPdfImgLocationMaxHeap() {
        return pdfImgLocationMaxHeap;
    }

    public void setPdfImgLocationMaxHeap(MaxHeap<PdfImgLocation> pdfImgLocationMaxHeap) {
        this.pdfImgLocationMaxHeap = pdfImgLocationMaxHeap;
    }

    /**
     * 1.排序
     * 2.矩形合并扫一遍就可以了
     * @return
     */
    public List<PdfImgLocation> mergePdfImgLocationList(){
        List<PdfImgLocation> mergeList = new LinkedList<>();
        PdfImgLocation pdfImgLocation = null;
        PdfImgLocation lastPdfImgLocation = null;
        while ((pdfImgLocation = pdfImgLocationMaxHeap.removeTop()) != null){
            if(lastPdfImgLocation==null){
                lastPdfImgLocation = pdfImgLocation;
            }else{
                //是否有交集
                if(lastPdfImgLocation.getEndPosition()>=pdfImgLocation.getPosition()){
                    if(lastPdfImgLocation.getEndPosition()>=pdfImgLocation.getEndPosition()){
                        //本合集直接忽略
                    }else{
                        //合并在一起
                        lastPdfImgLocation = new PdfImgLocation(lastPdfImgLocation.getPosition(),pdfImgLocation.getEndPosition()-lastPdfImgLocation.getPosition());
                    }
                }else{
                    mergeList.add(lastPdfImgLocation);
                    lastPdfImgLocation = pdfImgLocation;
                }
            }
        }
        mergeList.add(lastPdfImgLocation);
        return mergeList;
    }

}
