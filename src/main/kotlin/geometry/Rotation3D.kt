package mathx.geometry

// https://www.euclideanspace.com/maths/geometry/rotations/index.htm
public interface Rotation3D {
    public fun toBasis(): Basis3D
}

public class AxisAngle3D : Rotation3D {
    override fun toBasis(): Basis3D = TODO("Not yet implemented")
}

public class Euler3D : Rotation3D {
    override fun toBasis(): Basis3D = TODO("Not yet implemented")
}

public class Quaternion : Rotation3D {
    override fun toBasis(): Basis3D = TODO("Not yet implemented")
}
