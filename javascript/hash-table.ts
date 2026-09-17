// Hey guys this is a hash table implementation in TypeScript. It uses separate chaining to handle collisions. The hash function is a simple modulo operation based on the size of the table.

interface hashTableInterface {
    values: string[]
}

let myHashTableAsArray:hashTableInterface[] = []


//set bucket size it can be 5, 10, 15, 20, 100,1000, 10000,etc. Your size will determine the point where the the hash needs to be rehashed 
let bucketSize = 10


//temporary storage 
let tempStorage:string[] = []

//counter variable to track how many items has been added to the hash table
let counter:number = 0


//the hashcode is the key which is generated everytime you pass a value it to the hashfunction
function hashCodeFunction(value: string) {
    let key:number = 0

        for (let i = 0; i < value.length; i++) {
         key += value.charCodeAt(i)       
        }

        return key % bucketSize;
}

//load factor is the mathematics that i used to get the point where the hash needs to be rehashed or resized it is set to 0.75 or 75%
function loadFactor(totalSizeOfBucketCapacity:number, sizeOfValueinBucket: number) {
    return sizeOfValueinBucket / totalSizeOfBucketCapacity;
}


function rehash() {
    const existingValues:string[] = getBucket(); 
    // add more buckets to the current bucket 
    bucketSize *= 2; // Double the bucket size

    // 3. Reset table storage and tracking counters
    myHashTableAsArray = [];


    // then re add the values so it can get new position cause the size of the hash table is now different 
    for (let i = 0; i < existingValues.length; i++) {
        addRehashValue(existingValues[i]);
    }

    console.log("Rehashing done. New bucket size: " + bucketSize, myHashTableAsArray);
}



function getBucket() {
// temporary hash table array to track how many itmes has been added 
    let myHashTableResultArray:string[] = []

     //This for loop will loop over the array to check if there is something in each postion. If there is push them into myHashTableResultArray a a temporary array to calculate the load factor
    for (let i = 0; i < myHashTableAsArray.length; i++) {
        if(!myHashTableAsArray[i]) {
            continue
        }

        //puts each string into the array so you can get how many data has enterted into the hash table 
        myHashTableResultArray.push(...myHashTableAsArray[i].values)

    }

    return myHashTableResultArray
}


//This is the function to add value to the hashmap DS and also handle the collission case scenariao too 
function addValue(value: string) {

    let key =  hashCodeFunction(value)
    counter++


    if (!myHashTableAsArray[key]) {
        myHashTableAsArray[key] = {
            values: []
        };
    }
        
    myHashTableAsArray[key].values.push(value);

     if(counter /bucketSize > 0.75) {
        rehash();
    }

   return {
     isSaved: true,
     key: key
   }
}

function addRehashValue(value: string) {
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



console.log(addValue("amiola"))
console.log(addValue("demilade"));
console.log(addValue("mary"));
console.log(addValue("demilade"));
console.log(addValue("amiola"))
console.log(addValue("demilade"));
console.log(addValue("mary"));


    
console.log(addValue("demilade"));


    let dataStored = myHashTableAsArray;

    console.log(/*Hash Table Result */)
    console.log(dataStored)





    console.log(bucketSize)

// Amiola_dev




/*-------------------------------------------------------Result--------------------------------------------------------------- */
// { isSaved: true, key: 7 }
// { isSaved: true, key: 1 }
// { isSaved: true, key: 1 }
// { isSaved: true, key: 1 }
// { isSaved: true, key: 7 }
// { isSaved: true, key: 1 }
// { isSaved: true, key: 1 }
// Rehashing done. New bucket size: 20 [
//   <1 empty item>,
//   {
//     values: [ 'demilade', 'mary', 'demilade', 'demilade', 'mary', 'demilade' ]
//   },
//   <5 empty items>,
//   { values: [ 'amiola', 'amiola' ] }
// ]
// { isSaved: true, key: 1 }
// 20