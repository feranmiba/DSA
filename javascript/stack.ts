//Hello guys another series of DS and This is a stack implementation in TypeScript. It uses a LASt in First out (LIFO) basis. This is a linear data structure


class Stack {
    stack:string[] = []


    isEmpty() {
       return this.stack.length === 0 ? true : false
    }


    //pushing the value unto the stack
    push(value: string) {
        let currentlength = this.stack.length;
        this.stack[currentlength] = value
        return {
            isSuccess: true,
            value: value
        }
    }

    pop() {
        if(this.isEmpty()) {
            return "stack is empty"
        }
       // to pop out a value you first need to get the last item of the array   
       let lastItem = this.stack[this.stack.length - 1];

       this.stack.length =this.stack.length - 1

       return lastItem    
    }
}

const myStack = new Stack();

// 1. Empty
console.log(myStack.isEmpty());

// 2. Add
myStack.push("amiola");
myStack.push("demilade");
myStack.push("mary");

console.log(myStack.stack);

// 4. Pop
console.log(myStack.pop());
console.log(myStack.stack);


// 6. Remove everything
console.log(myStack.pop());
console.log(myStack.pop());

// 7. Pop empty stack
console.log(myStack.pop());