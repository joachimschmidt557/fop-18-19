import java.util.LinkedList;
import java.util.List;

public class V1 {

    public static void main(String[] args) {

        System.out.println("H");

    }

    public class ListItem<T> {
        public T key;
        public ListItem<T> next;
    }

    public class MyLinkedList<T> {

        private ListItem<T> head;

        public MyLinkedList() {
            head = null;
        }

        /**
         *
         * @param key
         */
        public void add(T key) {

            ListItem<T> itemToAdd = new ListItem<T>();
            itemToAdd.key = key;

            // If the linked list is empty, add
            if (head == null) {
                head = itemToAdd;
            } else {
                ListItem<T> nextItem = head.next;
                while (nextItem != null)
                    nextItem = nextItem.next;
                nextItem.next = itemToAdd;
            }

        }

        private ListItem<T> getListItemAt(int pos) {
            return null;
        }

        public void delete(int pos) {

        }

        public void delete(T key) {

        }

        public T beforeBeforeLast() {
            return null;
        }

        public void ringShiftLeft() {

        }

        public T[] listIntoArray(ListItem<T> lst, Class<?> type) {
             return null;
        }

    }

}
