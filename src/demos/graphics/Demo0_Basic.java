/****************************************************************
 * Šioje klasėje pateikamas įvadas į JavaFX grafiką
 * 
 * Pradžioje vykdykite kodą ir stebėkite atliekamus veiksmus
 * Užduotis atlikite sekdami nurodymus programinio kodo komentaruose
 * Gynimo metu atlikite dėstytojo nurodytas užduotis naujų metodų pagalba.
 *
 * @author Eimutis Karčiauskas, KTU programų inžinerijos katedra 2019 08 05
 **************************************************************************/
package demos.graphics;

import extendsFX.BaseGraphics;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Demo0_Basic extends BaseGraphics {
    
    public int x = 3;
        
    // pradžioje brėšime horizontalias ir vertikalias linijas per centrą
    private void drawHVtoCenter() {  
        gc.setLineWidth(3);       // brėžimo linijos plotis
        gc.setStroke(Color.CORAL);  // ir tos linijos spalva
        gc.strokeLine(0, canvasH / 2, canvasW, canvasH / 2);
        gc.strokeLine(canvasW / 2, 0, canvasW / 2, canvasH);
    }
    // po to brėšime įstrižaines per centrą
    private void drawXtoCenter() {
        gc.setLineWidth(4);         // brėžimo linijos plotis
        gc.setStroke(Color.DARKSEAGREEN);  // ir tos linijos spalva
        gc.strokeLine(0, 0, canvasW, canvasH);
        gc.strokeLine(0, canvasH, canvasW, 0);
    }  
// UŽDUOTIS_1: plonomis linijomis su žingsniu step=50 nubrėžkite tinklelį
    private void drawGrid1() { 
       double step = 50;
        gc.setLineWidth(0.5);         // linijos plotis galimai mažesnis
        for(double u = step; u < Math.max(canvasW, canvasH); u += step) {
           // gc.setStroke(u%100==0? Color.GREY: Color.GREY.brighter());  
            gc.strokeLine(0, u, canvasW, u);   // horizontalios linijos
            gc.strokeLine(u, 0, u, canvasH);   // vertikalios linijos
        }
    }
// https://examples.javacodegeeks.com/desktop-java/javafx/javafx-canvas-example/    
    private void drawExamples1() {
        double lw = 3.0;
        gc.setLineWidth(lw);        // brėžimo linijos plotis
        gc.setStroke(Color.CHARTREUSE);   // ir tos linijos spalva
        gc.setFill(Color.CORAL);      // dažymo spalva figūroms
        int x=10, y=10, w=80, h=50, 
            d=20, ax=10, ay=20; // d-tarpas tarp elementų, ax,ay-apvalinimai
        gc.strokeRoundRect(x, y, w, h, ax, ay);
        x+=w+d; // sekantis į dešinę
        gc.fillRoundRect(  x, y, w, h, ax, ay);
        gc.setLineWidth(0.5);
        gc.strokeText("Wolf and Bear", x, y+h);
        //-------------------
        gc.setLineWidth(2*lw);    // dvigubai pastoriname liniją      
        gc.setFill(Color.YELLOW);
        x = 10;    // grįžtame horizontaliai
        y += h+d;  // ir pereiname žemyn
        gc.strokeOval(x, y, w, h);
        x += w+d; // sekantis į dešinę
        gc.fillOval( x, y, h, w);
        x = 10;     // grįžtame horizontaliai
        y += h+2*d; // ir pereiname žemyn ir brėžiame lankus
        gc.strokeArc  (x, y, w, w, 30,  90, ArcType.ROUND);
        gc.fillArc(x+w+d, y, w, w, 45, 180, ArcType.OPEN);
    }  
    private void drawUnicode(){
        // išbandykite ir kitus simbolius
        // https://en.wikipedia.org/wiki/List_of_Unicode  skyrius 31
        StringBuilder sb = new StringBuilder();
        for(char ch = '\u2659'; ch <= '\u269F'; ch++)
            sb.append(ch);
        gc.setFont(Font.font("Lucida Console", 36));
        gc.setLineWidth(1);
        gc.setStroke(Color.BLACK);
        gc.strokeText(sb.toString(), 50, 350);
    }
// UŽDUOTIS_2: nubrėžkite polilinijas ir poligonus   
// https://www.tutorialspoint.com/javafx/2dshapes_polygon    
    private void drawExamples2() { 
    gc.setFill(Color.DARKSEAGREEN);
    gc.setStroke(Color.CHARTREUSE);
    gc.setLineWidth(5);
    gc.strokeLine(40, 10, 10, 40);
    gc.strokePolygon(new double[]{60, 90, 60, 90},
                     new double[]{210, 210, 240, 240}, 4);
    gc.strokePolyline(new double[]{110, 140, 110, 140},
                      new double[]{210, 210, 240, 240}, 4);
    }
// UŽDUOTIS_3: nubrėžkite taisyklingus 3, 4, 5, ..., 9-kampius  
    private void drawExamples3(int n) { 
        gc.setStroke(Color.CORAL);
        gc.setFill(Color.YELLOW);
        gc.setLineWidth(5);

        double r = 25;
        double theta = (360.0 / n) * Math.PI / 180.0;
        double w = 0;
        w = 130*(n-1);
        double h = 150;
        
        double x[] = new double[n];
        double y[] = new double[n];
        
        for (int i = 0; i < n; i++) {
            double x1 = r * Math.cos(theta * i);
            double y1 = r * Math.sin(theta * i);
            double x2 = r * Math.cos(theta * (i + 1));
            double y2 = r * Math.sin(theta * (i + 1));
            x[i] = x1 + w / 2;
            y[i] = -y1 + h / 2;
        }
        gc.strokePolygon(x, y, n);
    }
// UŽDUOTIS_4: nubrėžkite žiedus https://en.wikipedia.org/wiki/Olympic_symbols
    private void drawOlympicRings() { 
        gc.setStroke(Color.CHARTREUSE);
        gc.strokeOval(30, 30, 30, 30); 
        gc.setStroke(Color.YELLOW);
        gc.strokeOval(50, 45, 30, 30); 
        gc.setStroke(Color.BLACK);
        gc.strokeOval(70, 30, 30, 30); 
        gc.setStroke(Color.DARKSEAGREEN); 
        gc.strokeOval(90, 45, 30, 30); 
        gc.setStroke(Color.CORAL); 
        gc.strokeOval(110, 30, 30, 30); 
    }
// UŽDUOTIS_5: pasirinktinai nubrėžkite savo tematiką:
// kelių valstybių sudėtingesnes vėliavas http://flagpedia.net/index
// pvz: Pietų Afrikos, Makedonijos, Norvegijos, Graikijos, Britanijos, ...
// arba futbolo, krepšinio ar ledo ritulio aikštes su žaidėjų pozicijomis  
    private void drawFreeThema() { 
         // Load the Image
        String imagePath = "file:\\C:\\Users\\lukzil2\\Desktop\\Lab1A_intro\\Lab1a_IntroductionFX\\Georg.png";
        Image image = new Image(imagePath);
        // Draw the Image
        gc.drawImage(image, 0, 0, 273, 141);
    }  
    @Override
    public void createControls(){
         addButton("clear", e -> clearCanvas()); 
        addButton("grid",  e -> baseGrid());
        addButton("HVC",   e -> drawHVtoCenter());
        addButton("XC",    e -> drawXtoCenter());
        addButton("pvz1",  e -> drawExamples1());
        addButton("UniCode",  e -> drawUnicode());
        addButton("Polygonai",  e -> drawExamples2());
        addButton("n-kampiai", action -> drawExamples3(x++));
        addButton("Veliava",  e -> drawFreeThema());
        addButton("Olimpine", action -> drawOlympicRings());
        addButton("grid2",  e -> drawGrid1());
        addNewHBox();
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Braižymai Canvas lauke (KTU IF)");        
        setCanvas(Color.GREEN, 600, 400);
        super.start(stage);
    }       
    public static void main(String[] args) {
        launch(args);
    }    
}
