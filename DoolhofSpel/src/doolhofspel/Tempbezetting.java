package doolhofspel;

import java.awt.Image;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public abstract class Tempbezetting extends Veldbezetting{
    public Tempbezetting() {
    
    }
    
    @Override
    public void setImage(Image tempAfbeelding){
      super.setImage(tempAfbeelding);
    }
    
    @Override
    public Image getImage(){
        return super.getImage();
    }
    
    @Override
     public void setPositie(int x, int y){
        super.setPositie(x,y);
    }
    
    @Override
    public int getX(int x){
        return x = super.getX(x);
    }
    
    @Override
    public int getY(int y){
        return y = super.getY(y);
    }
    
    // deze methode moet coÖrdinaten meekrijgen van de positie van het object
//    public void verdwijnen(int veldX,int veldY) {
//        String pos = Integer.toString(veldX)+ Integer.toString(veldY);
//        int index = Integer.parseInt(pos);
//        
//        
//        
//        
//        ArrayList findBazooka = kaart.getMapObjects();
//            for (int i = 0; i < findBazooka.size(); i++) {
//                String value = findBazooka.get(i).toString();
//                if (value.contains("Bazooka")) {
//                    findBazooka.remove(i);
//                    Gras gras = new Gras();
//                    findBazooka.add(i, gras);
//                }
//            }
//    
}
