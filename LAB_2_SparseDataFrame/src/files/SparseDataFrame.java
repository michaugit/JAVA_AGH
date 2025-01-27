package files;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SparseDataFrame extends DataFrame {

    public SparseDataFrame(String[] colName, String[] type, Object hide){
        super(colName, type);
        this.hide=hide;
    }

    //Konstruktory z header jako String- jeśli jest podany to oznacza że nie ma nagłówka,
    // jeśli go nie ma, ocznacza to że csv posiada nagłówek w pierwszym wierszu.
    public SparseDataFrame(String inputFile, String[] type, Object hide) {
        try {
            // Open the file
            FileInputStream fstream = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //Read File Line By Line
            String[] colName;
            if ((strLine = br.readLine()) != null) {
                colName = strLine.split(",");
                this.tab = new ArrayList<Column>();
                this.hide = hide;
                this.size = 0;
                int iter = 0;
                for (String str : colName) {
                    tab.add(new Column(str, type[iter]));
                    iter++;
                }
            }

            while ((strLine = br.readLine()) != null) {
                String[] row = strLine.split(",");
                int itr = 0;
                for (String s : row) {
                    switch ((String) tab.get(itr).type) {
                        case "int":
                        case "Int":
                        case "integer":
                        case "Integer":
                            this.add(itr,Integer.parseInt(s));
                            break;
                        case "String":
                        case "string":
                            this.add(itr,s);
                            break;
                        case "double":
                        case "Double":
                            this.add(itr,Double.parseDouble(s));
                            break;
                        default:
                            System.out.println("Unknown type while parsing! :(");
                            break;
                    }
                    itr++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("something went wrong while reading the file!\n" + e.toString());
            System.exit(71830);

        }
    }
    public SparseDataFrame(String inputFile, String[] type, Object hide, String[] headerName ) {
        try {
            // Open the file
            FileInputStream fstream = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            this.tab = new ArrayList<Column>();
            this.hide = hide;
            this.size = 0;
            int iter = 0;
            for (String str : headerName) {
                tab.add(new Column(str, type[iter]));
                iter++;
            }


            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String[] row = strLine.split(",");
                int itr = 0;
                for (String s : row) {
                    switch ((String) tab.get(itr).type) {
                        case "int":
                        case "Int":
                        case "integer":
                        case "Integer":
                            this.add(itr,Integer.parseInt(s));
                            break;
                        case "String":
                        case "string":
                            this.add(itr,s);
                            break;
                        case "double":
                        case "Double":
                            this.add(itr,Double.parseDouble(s));
                            break;
                        default:
                            System.out.println("Unknown type while parsing! :(");
                            break;
                    }
                    itr++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("something went wrong while reading the file!\n" + e.toString());
            System.exit(71830);

        }
    }

    //Konstruktor z headerem jako boolean, gdy boolean false kolumny przyjmują nazwe "default".
    public SparseDataFrame(String inputFile, String[] type, Object hide, boolean header ) {
        try {
            // Open the file
            FileInputStream fstream = new FileInputStream(inputFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            //Read File Line By Line
            String[] colName;
            if ((strLine = br.readLine()) != null) {
                if (header) {
                    colName = strLine.split(",");
                } else {
                    colName = new String[type.length];
                    Arrays.fill(colName, "default");
                }
                this.tab = new ArrayList<Column>();
                this.hide = hide;
                this.size = 0;
                int iter = 0;
                for (String str : colName) {
                    tab.add(new Column(str, type[iter]));
                    iter++;
                }
                if (!header) {
                    String[] row = strLine.split(",");
                    int itr = 0;
                    for (String s : row) {
                        switch ((String) tab.get(itr).type) {
                            case "int":
                            case "Int":
                            case "integer":
                            case "Integer":
                                this.add(itr,Integer.parseInt(s));
                                break;
                            case "String":
                            case "string":
                                this.add(itr,s);
                                break;
                            case "double":
                            case "Double":
                                this.add(itr,Double.parseDouble(s));
                                break;
                            default:
                                System.out.println("Unknown type while parsing! :(");
                                break;
                        }
                        itr++;
                    }
                }
            }

            while ((strLine = br.readLine()) != null) {
                String[] row = strLine.split(",");
                int itr = 0;
                for (String s : row) {
                    switch ((String) tab.get(itr).type) {
                        case "int":
                        case "Int":
                        case "integer":
                        case "Integer":
                            this.add(itr,Integer.parseInt(s));
                            break;
                        case "String":
                        case "string":
                            this.add(itr,s);
                            break;
                        case "double":
                        case "Double":
                            this.add(itr,Double.parseDouble(s));
                            break;
                        default:
                            System.out.println("Unknown type while parsing! :(");
                            break;
                    }
                    itr++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("something went wrong while reading the file!\n" + e.toString());
            System.exit(71830);

        }
    }




    public void add(String colName, Object value){
        if(!value.equals(hide)) {
            COOValue obj = new COOValue(this.get(colName).index, value);
            this.get(colName).data.add(obj);
        }
        this.get(colName).index++;
    }
    public void add(int index, Object value){
        if(!value.equals(hide)) {
            COOValue obj = new COOValue(this.tab.get(index).index, value);
            this.tab.get(index).data.add(obj);
        }
        this.tab.get(index).index++;
    }
    public void print(){
        System.out.println("\n\nPRINTED COLUMN:");
        for( Column cln : this.tab){
            System.out.print("\nName of column: " + cln.name +
                    "\t\tType of column: " + cln.type +
                    "\t\tDane: ");
                    for(Object coo: cln.data){
                        COOValue value= (COOValue) coo;
                        System.out.print("("+((COOValue) coo).index + "," + ((COOValue) coo).value + ")");
                    }
        }
        System.out.println("\nNumber of rows: " + this.size());
    }

    public DataFrame toDense(){
       // this.adjustSizeOfColumns();
        DataFrame back= new DataFrame();
        for (Column cln: this.tab) {
            Column copy = new Column(cln.name, cln.type);
            if(cln.data.size()==0){
                while(copy.data.size() < cln.index) {
                    copy.data.add(hide);
                }
            }
            else {
                for (Object coo : cln.data) {
                    COOValue value = (COOValue) coo;
                    while(copy.data.size() < value.index) {
                        copy.data.add(hide);
                    }
                    if(copy.data.size() == value.index){
                        copy.data.add(value.value);
                    }
                }
            }
            while(copy.data.size() < cln.index) {
                copy.data.add(hide);
            }

            back.tab.add(copy);
        }
        return back;
    }
    Object hide;
}
