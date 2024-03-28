# DiffEase

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/Nuclominus/DiffEase?tab=Apache-2.0-1-ov-file)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.nuclominus/diffease.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.nuclominus/diffease)

A simple implementation of adapter for recycleview that implement DiffUtils and diffCallback by default.

The main idea of the library is to **completely abandon** the old implementation of updating the list via `notifyDataChanged()` and simplify the process of creating a generic and multi-type list with multiple view types.

Also in a library implemented variation of selectable helper for adapter.

## How to start:

Gradle Groovy DSL:
```groovy
implementation 'io.github.nuclominus:diffease:$diffeaseVersion'
```

Gradle Kotlin DSL:
```kotlin
implementation("io.github.nuclominus:diffease:$diffeaseVersion")
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
class SimpleDiffAdapter() : DiffEaseAdapter<MultiMock, BaseViewHolder<MultiMock>>() {
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

## Future features
- [ ] Support several selectable adapters in concatadapter
