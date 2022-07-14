# Recycler Diff Adapter
[![Maven Central](https://img.shields.io/maven-central/v/io.github.nuclominus/recyclerdiffadapter.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.nuclominus%22%20AND%20a:%22recyclerdiffadapter%22)

A simple implementation of adapter for recycleview that implement DiffUtils and diffCallback by default
The main idea of the library is to **completely abandon** the old implementation of updating the list via `notifyDataChanged()` and simplify the process of creating a generic and multi-type list with multiple view types.

Also in a liburary implemented variation of selectable helper for adapter.

## How to start:

Gradle Groovy DSL:
```groovy
implementation 'io.github.nuclominus:recyclerdiffadapter:$recycleDiffVersion'
```

Gradle Kotlin DSL:
```kotlin
implementation("io.github.nuclominus:recyclerdiffadapter:$recycleDiffVersion")
```

## Usage:

Using of this adapter is simple and almost does not differ from using a regular adapter. 
Using in three steps:

### First
```kotlin
/**
 * Implement adapter with specifying the data type and the supported data type. 
 * To support multiple cell types, we specify not an explicit viewholder type, but a generic one. 
 */
class SimpleDiffAdapter() : BaseListAdapter<MultiMock, BaseViewHolder<MultiMock>>() {
    ...
}
```

### Second
In the adapter you can/should change the default implementation of the diff callback

```kotlin
// override default implementation
override fun <TInput : MultiMock> getDiffCallback(
        oldItems: List<MultiMock>,
        newItems: List<TInput>
    ): DiffResultCallback<MultiMock, TInput> = baseDiffCallback(oldItems, newItems)
```

### Third
Update adapter

```kotlin
vm.data.observe(viewLifecycleOwner) { data ->
     adapter.update(data, true) // with detecting moves in list
}
```


## License

```
Copyright 2022 Roman Kosko

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0
   
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
