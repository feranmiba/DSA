// Hey guys this is a hash table implementation in TypeScript. It uses separate chaining to handle collisions. The hash function is a simple modulo operation based on the size of the table.

interface hashTableInterface {
    values: string[]
}

const myHashTableAsArray:hashTableInterface[] = []

//the hashcode is the key which is generated everytime you pass a value it to the hashfunction
function hashCodeFunction(value: string) {
    let key:number = 0

        for (let i = 0; i < value.length; i++) {
         key += value.charCodeAt(i)       
        }

        return key % 10;
}

//This is the function to add value to the hashmap DS and also handle the collission case scenariao too 
function addValue(value: string) {

    let key =  hashCodeFunction(value)

    if (!myHashTableAsArray[key]) {
        myHashTableAsArray[key] = {
            values: []
        };
    }

    myHashTableAsArray[key].values.push(value);

   return {
     isSaved: true,
     key: key
   }
}

//For checking/looking up a value in the hashmap on average case is constant time o(1) and for worstcase scenario is o(n).
function lookUpValue(value: string) {
    let key = hashCodeFunction(value)

    let valueRetrieved = myHashTableAsArray[key];

    let checkifWordExist = valueRetrieved.values.includes(value)

    return {
        key: key,
        value:  checkifWordExist ? valueRetrieved : "Sorry the word is not in the hash map",
        isThere: checkifWordExist ? true : false
    }
}

// The deleted value returs the value that we deleted
function deleteValue(value: string) {
     let key = hashCodeFunction(value)

    let valueRetrieved = myHashTableAsArray[key];

    let indexofString =  valueRetrieved.values.indexOf(value)

     if (indexofString === -1) {
            return null;
        }

    let newArr =  valueRetrieved.values.splice(indexofString, 1)

    return {
        key: key,
        value :  newArr

    }

}

console.log(addValue("demilade"));
console.log(addValue("mary"));
console.log(addValue("john"))


console.log(lookUpValue("demilade"))
console.log(deleteValue("demilade"))


console.log(lookUpValue("demilade"))



console.log( myHashTableAsArray)



// Amiola_dev