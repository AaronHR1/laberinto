public class Array2d {
    Character [][]data;
    int rowSize;
    int colSize;

    public Array2d(int ren, int col) {
        this.rowSize = ren;
        this.colSize = col;
        this.data = new Character[ren][col];
    }

    public void clear(Character dato){
        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
                this.data[i][j] = dato;
            }
        }
    }

    public int getRowSize() {
        return rowSize;
    }

    public int getColSize() {
        return colSize;
    }

    @Override
    public String toString() {
        String str = "";

        for (int i = 0; i < this.rowSize; i++) {
            for (int j = 0; j < this.colSize; j++) {
               str = str + this.data[i][j] + ", ";
            }
            str = str + "\n";
        }
        return str;
    }

    public void setItem(int ren, int col, Character dato){
        if (ren>=0 && ren < this.rowSize && col >= 0 && col < this.colSize){
            this.data[ren][col]=dato;
        }else{
            System.out.println("Indices fuera de rango");
        }

    }

    public Character getItem(int ren, int col){
        if (ren>=0 && ren < this.rowSize && col >= 0 && col < this.colSize){
            return this.data[ren][col];
        }else{
            //System.out.println("Indices fuera de rango");
        }
        return '\0';
    }


}
