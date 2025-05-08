package edu.udel.cisc475.numlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableNumListTest {
	//list coverage
    private ImmutableNumList empty;
    private NonemptyNumList list1; 
    private NonemptyNumList list123;
    private NonemptyNumList list923;
    private NonemptyNumList list123Dup; 
    private NonemptyNumList list000;
    private NonemptyNumList list101;
    @BeforeEach
    void setUp() {
        empty = ImmutableNumList.emptyList();
        list1 = empty.cons(1); 
        list123 = empty.cons(3).cons(2).cons(1); 
        list923 = list123.set(0, 9); 
        list123Dup = ImmutableNumList.emptyList().cons(3).cons(2).cons(1); 
        list000 = ImmutableNumList.emptyList().cons(0).cons(0).cons(0); 
        list101 = ImmutableNumList.emptyList().cons(1).cons(0).cons(1); 
    }

    @Test
    @DisplayName("emptyList() should return the same singleton instance")
    void testEmptyList() {
        assertSame(ImmutableNumList.emptyList(), empty, "should return empty list.");
    }

    @Nested
    @DisplayName("Tests for EmptyNumList")
    class EmptyNumListTests {

        @Test
        @DisplayName("isEmpty() should return true")
        void isEmptyForEmptyList() {
            assertTrue(empty.isEmpty(), "is empty test.");
        }

        @Test
        @DisplayName("size() should return 0")
        void sizeForEmptyList() {
            assertEquals(0, empty.size(), "size test");
        }

        @Test
        @DisplayName("cons() should create a NonemptyNumList")
        void consOnEmptyList() {
            NonemptyNumList newList = empty.cons(1);
            assertFalse(newList.isEmpty(), "List shoudl not be empty.");
            assertEquals(1, newList.first(), "should be 1 in the list.");
            assertTrue(newList.rest().isEmpty(), "rest of list should be empty.");
            assertEquals(1, newList.size(), "size should be one.");
        }

        @Test
        @DisplayName("contains() should give a false")
        void containsForEmptyList() {
            assertFalse(empty.contains(10), "should reutrn fulse as there is nothing in list.");
        }

        @Test
        @DisplayName("find() should return -1")
        void findForEmptyList() {
            assertEquals(-1, empty.find(10), "returns -1 on empty list.");
        }

        @Test
        @DisplayName("set() should throw IllegalArgumentException")
        void setOnEmptyList() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                empty.set(0, 5);
            });
            // Message from get message
            assertEquals("idx exceeds length of list", exception.getMessage());
        }

        @Test
        @DisplayName("toString() should return '{}'")
        void toStringForEmptyList() {
            assertEquals("{}", empty.toString(), "{}");
        }

        @Test
        @DisplayName("equals() should be correct")
        void equalsForEmptyList() {
            assertEquals(empty, ImmutableNumList.emptyList(), "two empty list should be equal.");
            assertNotEquals(empty, null, "empty list != null.");
            assertNotEquals(empty, list1, "empty list != nonemptylist.");
            assertEquals(empty, new EmptyNumList(), "empty list = empty list");
        }

        @Test
        @DisplayName("hashCode() should be consistent")
        void hashCodeForEmptyList() {
            assertEquals(empty.hashCode(), ImmutableNumList.emptyList().hashCode(), "hashcode should be the same.");
            // As per EmptyNumList.java, hashCode() returns a constant 234823
            assertEquals(234823, empty.hashCode());
        }
    }

    @Nested
    @DisplayName("Tests for NonemptyNumList")
    class NonemptyNumListTests {

        @Test
        @DisplayName("isEmpty() should return false")
        void isEmptyForNonemptyList() {
            assertFalse(list1.isEmpty(), "Nonempty list  should not be empty.");
            assertFalse(list123.isEmpty(), "Nonempty list should not be empty.");
        }

        @Test
        @DisplayName("first() should return the first element")
        void firstForNonemptyList() {
            assertEquals(1, list1.first(), "first() for list {1} should be 1.");
            assertEquals(1, list123.first(), "first() for list {1, 2, 3} should be 1.");
        }

        @Test
        @DisplayName("rest() should return the rest of the list")
        void restForNonemptyList() {
            assertTrue(list1.rest().isEmpty(), "rest() for list {1} should be an empty.");
            assertEquals(empty, list1.rest(), "viceversa prev test.");

            ImmutableNumList restOf123 = list123.rest(); // {2, 3}
            assertFalse(restOf123.isEmpty());
            assertEquals(2, ((NonemptyNumList) restOf123).first(), "First of rest of {1, 2, 3} should be 2.");

        }

        @Test
        @DisplayName("cons() should prepend an element")
        void consOnNonemptyList() {
            NonemptyNumList list01 = list1.cons(0); // {0, 1}
            assertEquals(0, list01.first(), " first element should be 0.");
            assertEquals(list1, list01.rest(), "Rest of the new list is original list1.");
            assertEquals(2, list01.size(), "Size is 2.");

            NonemptyNumList list0123 = list123.cons(0); // {0, 1, 2, 3}
            assertEquals(0, list0123.first());
            assertEquals(list123, list0123.rest());
            assertEquals(4, list0123.size());
        }

        @Test
        @DisplayName("size() should return the correct number of elements")
        void sizeForNonemptyList() {
            assertEquals(1, list1.size(), "Size of {1} should be 1.");
            assertEquals(3, list123.size(), "Size should be 3.");
            assertEquals(3, list923.size(), "Size should be 3.");
        }

        @Test
        @DisplayName("contains() should work correctly")
        void containsForNonemptyList() {
            assertTrue(list1.contains(1), "{1} does contain 1.");
            assertFalse(list1.contains(2), "{1} does not contain 2.");

            assertTrue(list123.contains(1), "{1, 2, 3} does contain 1 ");
            assertFalse(list123.contains(4), "{1, 2, 3} does not contain 4.");
        }

        @Test
        @DisplayName("find() should return correct index or -1 for not found")
        void findForNonemptyList() {
            assertEquals(0, list1.find(1), "should not find thus 0");

            assertEquals(0, list123.find(1), "find(1) in {1, 2, 3} should be 0 ");
            assertEquals(1, list123.find(2), "find(2) in {1, 2, 3} should be 1 ");
            assertEquals(2, list123.find(3), "find(3) in {1, 2, 3} should be 2 ");
            
            assertEquals(-1, list123.find(4), "find(4) in {1,2,3} should be -1");
        }


        @Test
        @DisplayName("set() should replace element at valid index")
        void setOnNonemptyListValidIndex() {
            NonemptyNumList changed0 = list123.set(0, 9); // Should be {9, 2, 3}
            assertEquals(9, changed0.first(), "Set index 0 of {1,2,3} to 9. First should be 9.");
            assertEquals(3, changed0.size(), "Size should remain 3.");
            assertEquals("{ 9 2 3 }", changed0.toString());


            // Test immutability
            assertEquals("{ 1 2 3 }", list123.toString(), "Original list should be immutable.");
            assertEquals(1, list123.first()); // Double check original list123 integrity
        }

        @Test
        @DisplayName("set() with negative index should throw exception")
        void setOnNonemptyListNegativeIndex() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                list123.set(-1, 9);
            });
            assertEquals("Illegal index", exception.getMessage());
        }

        @Test
        @DisplayName("set() with index out of bounds (too large) should throw from EmptyNumList")
        void setOnNonemptyListIndexTooLarge() {
            Exception exception = assertThrows(IllegalArgumentException.class, () -> {
                list123.set(3, 9);
            });
            assertEquals("idx exceeds length of list", exception.getMessage(),
                "set() with index equal to size should call set on EmptyNumList and throw an exception.");

        }


        @Test
        @DisplayName("toString() should return correct string representation")
        void toStringForNonemptyList() {
            assertEquals("{ 1 }", list1.toString());
            assertEquals("{ 1 2 3 }", list123.toString());
            assertEquals("{ 9 2 3 }", list923.toString());

        }
        
        @Test
        @DisplayName("toString() for a list created with multiple cons and set operations")
        void toStringComplexList() {
            ImmutableNumList l = ImmutableNumList.emptyList();
            l = l.cons(5); // {5}
            l = l.cons(4); // {4 5}
            l = l.cons(7); // {3 4 5}
            l = ((NonemptyNumList)l).set(1, 8); // {7 8 5}
            l = l.cons(2); // {2 3 8 5}
            l = ((NonemptyNumList)l).set(0, 1); // {1 7 8 5}
            l = ((NonemptyNumList)l).set(3, 9); // {1 7 8 9}
            assertEquals("{ 1 7 8 9 }", l.toString());
        }


        @Test
        @DisplayName("equals() should correctly compare NonemptyNumLists")
        void equalsForNonemptyList() {
            assertEquals(list123, list123Dup, "Two lists with same elements areequal.");
            assertNotEquals(list123, list1, "Lists of different sizes should not be equal.");
            assertNotEquals(list123, list923, "Lists with different elements should not be equal");

            NonemptyNumList list124 = empty.cons(4).cons(2).cons(1); // {1, 2, 4}
            assertNotEquals(list123, list124, "Lists with different elements should not be equal ");
            
            NonemptyNumList list132 = empty.cons(2).cons(3).cons(1); // {1, 3, 2}
            assertNotEquals(list123, list132, "Lists with same elements in different order not equal.");

            assertNotEquals(list123, null, "Nonempty list should not be equal to null.");
            assertNotEquals(list123, empty, "Nonempty list should not be equal to empty list.");
            assertNotEquals(list123, Integer.valueOf	(1), "Nonempty list should not be equal to an object of different type.");
        }

        @Test
        @DisplayName("hashCode() should be consistent with equals()")
        void hashCodeForNonemptyList() {
            assertEquals(list123.hashCode(), list123Dup.hashCode(), "Hash codes for equal lists should be equal.");
            int expectedHashCodeList1 = 1 + empty.hashCode();
            assertEquals(expectedHashCodeList1, list1.hashCode());

            int expectedHashCode123 = 1 + (2 + (3 + empty.hashCode()));
            assertEquals(expectedHashCode123, list123.hashCode());

            assertNotEquals(list1.hashCode(), list123.hashCode(), 
                "Hash codes for different list differ.");
            assertNotEquals(list123.hashCode(), list923.hashCode(), 
                    "Hash codes for different list differ.");
        }
        
    }
    @Nested
    @DisplayName("Tests for New Methods")
    class CISC675Tests {

        // --- append() Tests ---
        @Test
        @DisplayName("apending empty to empty shoudl be empty")
        void appendEmptyToEmpty() {
            assertEquals(empty, empty.append(empty), "empty.append(empty) should be {}");
        }

        @Test
        @DisplayName("append empty first way")
        void appendList123ToEmpty() {
            assertEquals(list123, empty.append(list123), "empty.append({1,2,3}) should be {1,2,3}");
        }

        @Test
        @DisplayName("append empty both ways")
        void appendEmptyToList123() {
            assertEquals(list123, list123.append(empty), "{1,2,3}.append(empty) should be {1,2,3}");
        }

        @Test
        @DisplayName("append: list1.append(list123) should be {1,1,2,3}")
        void appendList1ToList123() {
            ImmutableNumList expected = empty.cons(3).cons(2).cons(1).cons(1); 
            ImmutableNumList result = list1.append(list123);
            assertEquals(expected, result, "lists shoudl be teh same");
        }

        @Test
        @DisplayName("append: list123.append(list1) should be {1,2,3,1} other way of append")
        void appendList123ToList1() {
            ImmutableNumList expected = empty.cons(1).cons(3).cons(2).cons(1); 
            ImmutableNumList result = list123.append(list1);
            assertEquals(expected, result, "lists should be same");
        }

        @Test
        @DisplayName("append: check immutability of original lists after append")
        void appendImmutability() {
            String originalList1String = list1.toString();
            
            list1.append(list123);
            
            assertEquals(originalList1String, list1.toString(), "list should be immutable");
        }

        @Test
        @DisplayName("removeZeros: empty list should remain empty")
        void removeZerosFromEmpty() {
            assertEquals(empty, empty.removeZeros(), "removeZeros from empty should be empty");
            assertEquals("{}", empty.removeZeros().toString());
        }

        @Test
        @DisplayName("removeZeros: list123 (no zeros) should be the same")
        void removeZerosFromListWithNoZeros() {
            assertEquals(list123, list123.removeZeros(), "should be same");
        }
        
        @Test
        @DisplayName("removeZeros: list1 (no zeros) should remain unchanged")
        void removeZerosFromList1WithNoZeros() {
            assertEquals(list1, list1.removeZeros(), "{1}.removeZeros() should be {1} as it has no zeros");
        }
        @Test
        @DisplayName("list000 should be empty")
        void removeZeroesWithZeroes() {
            assertEquals(empty, list000.removeZeros(), "{0,0,0} GOES TO empty");
        }
        
        @Test
        @DisplayName("list101 should be {1,1}")
        void removeList101() {
            assertEquals("{ 1 1 }", list101.removeZeros(), "{1,0,1} goes to {1,1}");
        }

      
        @Test
        @DisplayName("removeZeros: check immutability (on list with no zeros)")
        void removeZerosImmutability() {
            String originalList123 = list123.toString();
            list123.removeZeros();
            assertEquals(originalList123, list123.toString(), "Original list123 should not change after removeZeros");
            String originalList000 = list000.toString();
            list000.removeZeros();
            assertEquals(originalList000, list000.toString(), "Original list000 should not change after removeZeros");

        }

        // --- addToAll() Tests ---
        @Test
        @DisplayName("addToAll: adding to empty list should result in empty list")
        void addToAllToEmpty() {
            assertEquals(empty, empty.addToAll(5), "addToAll(5) to empty should be empty");
        }

        @Test
        @DisplayName("addToAll: adding 0 to list123 should not change the list")
        void addToAllZeroToList123() { 
            assertEquals(list123, list123.addToAll(0), "{1,2,3}.addToAll(0) should be {1,2,3}");
        }

        @Test
        @DisplayName("addToAll: adding 5 to list123")
        void addToAllPositiveToList123() { 
            ImmutableNumList result = list123.addToAll(5);
            assertEquals("{ 6 7 8 }", result.toString());
        }
        
        @Test
        @DisplayName("addToAll: adding 5 to list1")
        void addToAllPositiveToList1() {
            ImmutableNumList result = list1.addToAll(5);
            assertEquals("{ 6 }", result.toString());
        }


        @Test
        @DisplayName("addToAll: adding -1 to list123")
        void addToAllNegativeToList123() { 
            ImmutableNumList result = list123.addToAll(-1);
            assertEquals("{ 0 1 2 }", result.toString());
        }
        
        @Test
        @DisplayName("addToAll: check immutability")
        void addToAllImmutability() {
            String originalList123 = list123.toString();
            list123.addToAll(10);
            assertEquals(originalList123, list123.toString(), "Original list123 should not change after addToAll");

        }
    }
}
    
