@file:Suppress("NOTHING_TO_INLINE")

package mathx

import kotlin.math.abs

// Int

public inline fun nearest(x: Int, a: Int, b: Int): Int = if (abs(x - b) < abs(x - a)) b else a

public inline fun chunkOffset(x: Int, size: Int, origin: Int = 0): Int = (x - origin).mod(size)
public inline fun chunkStart(x: Int, size: Int, origin: Int = 0): Int = x - chunkOffset(x, size, origin)
public inline fun chunkIndex(x: Int, size: Int, origin: Int = 0): Int =
    (chunkStart(x, size, origin) - origin) / size

// Long

public inline fun nearest(x: Long, a: Long, b: Long): Long = if (abs(x - b) < abs(x - a)) b else a

public inline fun chunkOffset(x: Long, size: Long, origin: Long = 0): Long = (x - origin).mod(size)
public inline fun chunkStart(x: Long, size: Long, origin: Long = 0): Long = x - chunkOffset(x, size, origin)
public inline fun chunkIndex(x: Long, size: Long, origin: Long = 0): Long =
    (chunkStart(x, size, origin) - origin) / size
