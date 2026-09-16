import java.util.ArrayList;
import java.util.List;

// Hello guys another series of DS and This is a stack implementation in Java. It uses a Last in First out (LIFO) basis. This is a linear data structure

public class Stack {
    public List<String> stack = new ArrayList<>();

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    // Helper class to match TypeScript's { isSuccess: true, value: value } return object
    public static class PushResult {
        public boolean isSuccess;
        public String value;

        public PushResult(boolean isSuccess, String value) {
            this.isSuccess = isSuccess;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{ isSuccess: " + isSuccess + ", value: '" + value + "' }";
        }
    }

    // pushing the value unto the stack
    public PushResult push(String value) {
        this.stack.add(value);
        return new PushResult(true, value);
    }

    public String pop() {
        if (this.isEmpty()) {
            return "stack is empty";
        }
        // to pop out a value you first need to get the last item of the array
        int lastIndex = this.stack.size() - 1;
        String lastItem = this.stack.get(lastIndex);

        this.stack.remove(lastIndex);

        return lastItem;
    }

    public static void main(String[] args) {
        Stack myStack = new Stack();

        // 1. Empty
        System.out.println(myStack.isEmpty());

        // 2. Add
        myStack.push("amiola");
        myStack.push("demilade");
        myStack.push("mary");

        System.out.println(myStack.stack);

        // 4. Pop
        System.out.println(myStack.pop());
        System.out.println(myStack.stack);

        // 6. Remove everything
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());

        // 7. Pop empty stack
        System.out.println(myStack.pop());
    }
}
