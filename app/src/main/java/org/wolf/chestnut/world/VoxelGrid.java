package org.wolf.chestnut.world;

import org.wolf.chestnut.vector.Vec3;
import org.wolf.chestnut.vector.Vec4;
import org.wolf.chestnut.vector.IVec3;

public class VoxelGrid {
    private Vec4[] voxels;
    private IVec3 size;

    public VoxelGrid(IVec3 _size) {
        size = _size;
        voxels = new Vec4[size.getX() * size.getY() * size.getZ()];
        for(int i = 0; i < voxels.length; i++) {
            voxels[i] = new Vec4(0f, 0f, 0f, 0f);
        }
    }

    public IVec3 raycast(Vec3 position, Vec3 direction, Vec3 gridPos, Vec3 gridRot, Vec3 gridScale) {
        Vec3 pos = Vec3.mul(Vec3.sub(position, gridPos).rotate(gridRot.negate()), gridScale.reciprocal());
        Vec3 dir = direction.rotate(gridRot.negate());

        int t = 0;

        while(t < 10000) {
            if(pos.toIVec3().getX() >= 0 && pos.toIVec3().getX() < size.getX()) {
                if(pos.toIVec3().getY() >= 0 && pos.toIVec3().getY() < size.getY()) {
                    if(pos.toIVec3().getZ() >= 0 && pos.toIVec3().getZ() < size.getZ()) {
                        if(getVoxel(pos.toIVec3()).getW() != 0f) {
                            return pos.toIVec3();
                        }
                    }
                }
            }

            pos = Vec3.add(pos, dir.mul(0.1f));
            t++;
        }

        return new IVec3(-1, -1, -1);
    }

    public Vec4 getVoxel(IVec3 position) {
        if(position.getX() < 0) return new Vec4(0f, 0f, 0f, 0f);
        if(position.getX() >= size.getX()) return new Vec4(0f, 0f, 0f, 0f);
        if(position.getY() < 0) return new Vec4(0f, 0f, 0f, 0f);
        if(position.getY() >= size.getY()) return new Vec4(0f, 0f, 0f, 0f);
        if(position.getZ() < 0) return new Vec4(0f, 0f, 0f, 0f);
        if(position.getZ() >= size.getZ()) return new Vec4(0f, 0f, 0f, 0f);
        
        return voxels[position.getX() + size.getX() * position.getY() + size.getX() * size.getY() * position.getZ()];
    }

    public void setVoxel(IVec3 position, Vec4 color) {
        if(position.getX() < 0) return;
        if(position.getX() >= size.getX()) return;
        if(position.getY() < 0) return;
        if(position.getY() >= size.getY()) return;
        if(position.getZ() < 0) return;
        if(position.getZ() >= size.getZ()) return;

        voxels[position.getX() + size.getX() * position.getY() + size.getX() * size.getY() * position.getZ()] = color;
    }

    public IVec3 getSize() {
        return size;
    }
}