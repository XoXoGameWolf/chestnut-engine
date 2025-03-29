package org.wolf.chestnut.util;

import org.wolf.chestnut.vector.IVec3;

public class HitResult {
    private boolean hit;
    private float distance;
    private IVec3 position;

    public HitResult(boolean _hit, float _distance, IVec3 _position) {
        hit = _hit;
        distance = _distance;
        position = _position;
    }

    public boolean getHit() {
        return hit;
    }

    public float getDistance() {
        return distance;
    }

    public IVec3 getPosition() {
        return position;
    }
}