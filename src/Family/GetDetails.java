package Family;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

// Amith M - 9035093734

public class GetDetails {
    /**
     * Get Mother for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> mother(Stream<Family.Member> members) {
        return members.map(member -> member.getMother());
    }
    
    /**
     * Get Father for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> father(Stream<Family.Member> members) {
        return members.map(member -> member.getFather());
    }

    /**
     * Get Spouse for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> spouse(Stream<Family.Member> members) {
        return members.map(member -> member.getSpouse());
    }

    public static Stream<Family.Member> children(Stream<Family.Member> members) {
        return members
            .map(member -> member.getChildren())
            .flatMap(Collection::stream);
    }

    //Filter Stream<members> based on Gender
    private static Stream<Family.Member> all(Gender gender, Stream<Family.Member> members) {
        return members.filter(sibling -> sibling.getGender() == gender);
    }

    /**
     * Get all MALE children for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> sons(Stream<Family.Member> members) {
        return all(Gender.MALE, children(members));
    }

    /**
     * Get all FEMALE children for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> daughters(Stream<Family.Member> members) {
        return all(Gender.FEMALE, children(members));
    }

    /**
     * Get all children from Same mother, except self, for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> siblings(Stream<Family.Member> members) {
        return members
            .map((member) -> {
                final Set<Family.Member> children = new HashSet<>(member.getMother().getChildren());
                if(!children.isEmpty()) children.remove(member);
                return children;
            })
            .flatMap(Collection::stream);
    }

    /**
     * Get all MALE siblings for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> brothers(Stream<Family.Member> members) {
        return all(Gender.MALE, siblings(members));
    }

    /**
     * GET all FEMALE siblings for Stream<members>
     * @param members
     * @return
     */
    public static Stream<Family.Member> sisters(Stream<Family.Member> members) {
        return all(Gender.FEMALE, siblings(members));
    }
}