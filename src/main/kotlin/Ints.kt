@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.abs

// Int

public inline fun nearest(x: Int, a: Int, b: Int): Int = if (abs(x - b) < abs(x - a)) b else a

public inline fun chunkOffset(x: Int, size: Int): Int = x.mod(size)
public inline fun chunkStart(x: Int, size: Int): Int = x - chunkOffset(x, size)
public inline fun chunkIndex(x: Int, size: Int): Int = chunkStart(x, size) / size

// Long

public inline fun nearest(x: Long, a: Long, b: Long): Long = if (abs(x - b) < abs(x - a)) b else a

public inline fun chunkOffset(x: Long, size: Long): Long = x.mod(size)
public inline fun chunkStart(x: Long, size: Long): Long = x - chunkOffset(x, size)
public inline fun chunkIndex(x: Long, size: Long): Long = chunkStart(x, size) / size
