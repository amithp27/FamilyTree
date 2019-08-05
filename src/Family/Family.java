package Family;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Family {

    //Dummy
    public static final String UNDEFINED = "UNKOWN";
    
    //List of all members
    private HashMap<String, Member> members = new HashMap<>();

    /**
     * Adding undefined as dummy member when initializing Family
     */
    public Family(Consumer<Family> fillFamilyTree) {
        this.addMember(UNDEFINED, null);
        fillFamilyTree.accept(this);
    }

    /**
     * Add new Member. Check whether member already exists, 
     * if yes return existing member, 
     * if not create and return new member
     * @param name
     * @param gender
     * @return
     */
    public Member addMember(String name, Gender gender) {
        if(!members.containsKey(name)) {
            final Member newMember = new Member(name, gender);
            members.put(name, newMember);
            return newMember;
        } else {
            return members.get(name);
        }
    }

    /**
     * Get existing member from list of members. Throw runtime exception if not found.
     * @param name
     * @return
     */
    public Member getMember(String name) {
        if (members.containsKey(name)) {
            return members.get(name);
        } else {
            throw new MemberNotFoundException();
        }
    }

    /**
     * Exception if Member does not exist
     */
    public class MemberNotFoundException extends RuntimeException {}

    /**
     * Member Class
     * Subclass of Family
     * Each member in the Family would be of this type
     */
    public class Member {
        private String name;
        private Gender gender;
        private Member spouse;
        private Member father;
        private Member mother;
        private Set<Member> children = new HashSet<>();
        
        private Member(String name, Gender gender){
            this.name = name;
            this. gender = gender;
            this.spouse = members.get(UNDEFINED);
            this.father = members.get(UNDEFINED);
            this.mother = members.get(UNDEFINED);
        }

        public String getName() {
            return this.name;
        }

        public Gender getGender() {
            return this.gender;
        }

        public Member getSpouse() {
            return this.spouse;
        }

        public Member getFather() {
            return this.father;
        }

        public Member getMother() {
            return this.mother;
        }
        
        public Set<Member> getChildren() {
            return this.children;
        }

        public void isSpouseOf(Member member) {
            this.spouse = member;
            member.spouse = this;
        }

        public void isParentOf(Member member) {
            //Add to list of children 
            this.children.add(member);
            //Add to list of spouse's children
            this.spouse.children.add(member);
            //Set father/mother of new member based on gender
            if(this.gender == Gender.MALE) {
                member.father = this;            
            } else {
                member.mother = this;
            }
        }

        @Override
        public boolean equals(Object newObj) {
            //Directly check if both objects are same
            if(this == newObj) {
                return true;
            }
            //Check if object is instance of Member class, or not
            if (!(newObj instanceof Member)) {
                return false;
            }
            //Cast new object to Member object, and compare the names
            Member newMember = (Member) newObj;
            if ((this.name!=null) && (newMember.name!=null) && (!this.name.equals(newMember.name))){
                return false;
            } else {
                return true;
            }

        }

        @Override
        public String toString() {
            //Return name of the Member
            return this.getName();
        }

    }
}
