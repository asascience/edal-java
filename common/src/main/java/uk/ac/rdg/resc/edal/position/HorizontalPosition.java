/*******************************************************************************
 * Copyright (c) 2013 The University of Reading
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the University of Reading, nor the names of the
 *    authors or contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 ******************************************************************************/

package uk.ac.rdg.resc.edal.position;

import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * Defines the position of a point in the horizontal plane.
 * 
 * @author Jon Blower
 * @author Guy
 */
public class HorizontalPosition {

    private double x;
    private double y;
    private CoordinateReferenceSystem crs;

    public HorizontalPosition(double x, double y, CoordinateReferenceSystem crs) {
        this.x = x;
        this.y = y;
        this.crs = crs;
    }

    /**
     * Returns the x coordinate of this position
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y coordinate of this position
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a two-dimensional coordinate reference system. The first
     * coordinate in the CRS is the {@link #getX() x coordinate}; the second is
     * the {@link #getY() y coordinate}.
     * 
     * @return a two-dimensional coordinate reference system
     */
    public CoordinateReferenceSystem getCoordinateReferenceSystem() {
        return crs;
    }
}