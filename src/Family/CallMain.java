package Family;

import Family.Family;
import Family.Gender;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Amith M - 9035093734

public class CallMain {
    public static void main(String[] args) throws FileNotFoundException {
        Family fam1 = new Family();

        //Setting up KingShan and Queen Anga
        Family.Member king = fam1.addMember(Utility.KINGSHAN, Gender.MALE);
        Family.Member queen = fam1.addMember(Utility.QUEENANGA, Gender.FEMALE);
        king.isSpouseOf(queen);
        queen.isSpouseOf(king);

        //Read from input file
        CallMain.readInputs(fam1);
    }

    public static void readInputs(Family fam) throws FileNotFoundException {
        File file = new File(Utility.INPUT_FILE_PATH);
        Scanner sc = new Scanner(file); 
        //Using array to store params from each line.
        String[] str = new String[4];
        while (sc.hasNextLine()) {
            str = sc.nextLine().split(Utility.SPACE);
            if(str[0].isEmpty() || str[0].equals(Utility.COMMENTS)){
                continue;
            } else {
                //Assuming that first mentioned member always exists already
                //if second member doesn't exist, we create new one.
                if (str[0].equals(Utility.ADD_CHILD)) {
                    //Format : ADD_CHILD vyas krpi Female
                    //Syntax : ADD_CHILD <mothers_name> <childs_name> <gender_of_child>
                    //Array  : str[0]       str[1]          str[2]          str[3]
                    Family.Member mem1 = fam.getMember(str[1]);
                    Family.Member mem2 = (fam.getMember(str[2]) == null) ? fam.addMember(str[2], str[3].toUpperCase().equals("MALE")?Gender.MALE:Gender.FEMALE) : fam.getMember(str[2]);
                    mem1.isParentOf(mem2);
                    mem1.getSpouse().isParentOf(mem2);
                } else if (str[0].equals(Utility.ADD_SPOUSE)) {
                    //Format : ADD_SPOUSE vyas krpi Female
                    //Syntax : ADD_SPOUSE <name> <name_of_spouse> <gender_of_spouse>
                    //Array  :  str[0]    str[1]    str[2]              str[3]
                    Family.Member mem1 = fam.getMember(str[1]);
                    Family.Member mem2 = (fam.getMember(str[2]) == null) ? fam.addMember(str[2], str[3].toUpperCase().equals("MALE")?Gender.MALE:Gender.FEMALE) : fam.getMember(str[2]);
                    mem1.isSpouseOf(mem2);
                    mem2.isSpouseOf(mem1);
                } else if (str[0].equals(Utility.GET_RELATIONSHIP)) {
                    //Format : GET_RELATIONSHIP jaya SISTER_IN_LAW
                    //Syntax : GET_RELATIONSHIP <name> <Relationship>
                    //Array  :      str[0]       str[1]     str[2]
                    if(fam.getMember(str[1]) != null) {
                        Family.Member mem3 = fam.getMember(str[1]);
                        System.out.print(str[2] + " of " + str[1] + " are : " );
                        switch(str[2].toUpperCase()) {
                            case Utility.BROTHER : System.out.print( Relationships.BROTHER.to(mem3)); break;
                            case Utility.CHILDREN : System.out.print( Relationships.CHILDREN.to(mem3)); break;
                            case Utility.BROTHER_IN_LAW : System.out.print( Relationships.BROTHER_IN_LAW.to(mem3)); break;
                            case Utility.COUSIN : System.out.print( Relationships.COUSIN.to(mem3)); break;
                            case Utility.FATHER : System.out.print( Relationships.FATHER.to(mem3)); break;
                            case Utility.GRANDDAUGHTER : System.out.print( Relationships.GRANDDAUGHTER.to(mem3)); break;
                            case Utility.GRANDSON : System.out.print( Relationships.GRANDSON.to(mem3)); break;
                            case Utility.GRANDCHILDREN : System.out.print( Relationships.GRANDCHILDREN.to(mem3)); break;
                            case Utility.DAUGHTER : System.out.print( Relationships.DAUGHTER.to(mem3)); break;
                            case Utility.MATERNAL_AUNT : System.out.print( Relationships.MATERNAL_AUNT.to(mem3)); break;
                            case Utility.MATERNAL_UNCLE : System.out.print( Relationships.MATERNAL_UNCLE.to(mem3)); break;
                            case Utility.MOTHER : System.out.print( Relationships.MOTHER.to(mem3)); break;
                            case Utility.SISTER : System.out.print(Relationships.SISTER.to(mem3)); break;
                            case Utility.SISTER_IN_LAW : System.out.print( Relationships.SISTER_IN_LAW.to(mem3)); break;
                            case Utility.SPOUSE : System.out.print(Relationships.SPOUSE.to(mem3)); break;
                            case Utility.SON : System.out.print(Relationships.SON.to(mem3)); break;
                            case Utility.SIBLINGS : System.out.print(Relationships.SIBLINGS.to(mem3)); break;
                            case Utility.PATERNAL_AUNT : System.out.print( Relationships.PATERNAL_AUNT.to(mem3)); break;
                            case Utility.PATERNAL_UNCLE : System.out.print( Relationships.PATERNAL_UNCLE.to(mem3)); break;
                            default: System.out.print(Utility.INVALID_RELATIONSHIP);
                        }
                    } else {
                        System.out.print(Utility.PERSON_NOT_FOUND);
                    }
                    System.out.printf("\n" + Utility.LINE + "\n");
                }
            }
        }
        sc.close();
    }
}