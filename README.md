# WriteSonicClient
> Console application which calls the `writesonic.com` AI API, the response is printed out to the console.  
> 
> with the `--voice` option the output will be spoken out using the `freetts` API

## Requirements

- Java 17
- `writesonic.com` API Key ([how to obtain the WriteSonic API Key](https://docs.writesonic.com/reference/finding-your-api-key))

## Build

    ./mvnw clean package

## Run
> before running the application you need to set the `WRITE_SONIC_API_KEY` env variable.  
>  
> **Linux/Mac**: `export WRITE_SONIC_API_KEY=XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXX`  
> **Windows**: `set WRITE_SONIC_API_KEY=XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXX`

**text only**

    java -jar target/WriteSonicClient-1.0.0.jar

**with voice and text**

    java -jar target/WriteSonicClient-1.0.0.jar --voice 

> when you are done just **enter an empty line to close the application**

### Example output

    [+] Enter your question: 
    > provide me a java implementation of the heapsort algorythm in markdown
    [+] Processing question: provide me a java implementation of the heapsort algorythm in markdown
    ```Java
    public void heapSort(int[] list) { 
        // Build heap (rearrange array) 
        for (int i = list.length / 2 - 1; i >= 0; i--) 
            heapify(list, list.length, i); 
      
        // One by one extract an element from heap 
        for (int i=list.length-1; i>=0; i--) 
        { 
            // Move current root to end 
            int temp = list[0]; 
            list[0] = list[i]; 
            list[i] = temp; 
      
            // call max heapify on the reduced heap 
            heapify(list, i, 0); 
        } 
    } 
    
    // To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(int arr[], int n, int i) 
    { 
        int largest = i; // Initialize largest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
      
        // If left child is larger than root 
        if (l  n && arr[l] > arr[largest]) 
            largest = l; 
      
        // If right child is larger than largest so far 
        if (r  n && arr[r] > arr[largest]) 
            largest = r; 
      
        // If largest is not root 
        if (largest != i) 
        { 
            int swap = arr[i]; 
            arr[i] = arr[largest]; 
            arr[largest] = swap; 
      
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, largest); 
        } 
    } 
    ```