/*******************************************************************************
 * Copyright (c) 2014 The University of Reading
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

package uk.ac.rdg.resc.edal.dataset;

import java.util.Collection;
import java.util.Set;

import org.joda.time.DateTime;

import uk.ac.rdg.resc.edal.domain.Extent;
import uk.ac.rdg.resc.edal.domain.TrajectoryDomain;
import uk.ac.rdg.resc.edal.exceptions.DataReadingException;
import uk.ac.rdg.resc.edal.feature.Feature;
import uk.ac.rdg.resc.edal.feature.MapFeature;
import uk.ac.rdg.resc.edal.feature.PointSeriesFeature;
import uk.ac.rdg.resc.edal.feature.ProfileFeature;
import uk.ac.rdg.resc.edal.feature.TrajectoryFeature;
import uk.ac.rdg.resc.edal.geometry.BoundingBox;
import uk.ac.rdg.resc.edal.grid.HorizontalGrid;
import uk.ac.rdg.resc.edal.grid.TimeAxis;
import uk.ac.rdg.resc.edal.grid.VerticalAxis;
import uk.ac.rdg.resc.edal.position.HorizontalPosition;
import uk.ac.rdg.resc.edal.position.VerticalPosition;

public interface ContinuousDomainDataset<F extends Feature<?>> extends Dataset<F> {
    /**
     * Extracts a {@link MapFeature} from this {@link ContinuousDomainDataset}.
     * 
     * @param varIds
     *            The variables to extract. If this is <code>null</code>, all
     *            available variables (determined by the result of
     *            {@link ContinuousDomainDataset#getVariableIds()}) will be read
     * @param hGrid
     *            The target {@link HorizontalGrid}
     * @param targetZ
     *            The target z-position from which the map value will be read.
     *            The value in the resulting {@link MapFeature} will be the
     *            value at the elevation which is closest to this value. If this
     *            is <code>null</code>, the position closest to the upper value
     *            of zExtent will be used.
     * @param zExtent
     *            The vertical range to extract data from. Any features which
     *            have values within this vertical range (as well as the
     *            specified {@link HorizontalGrid} and time range) will be read.
     * @param targetTime
     *            The target datetime at which the map value will be read. The
     *            value in the resulting {@link MapFeature} will be the value at
     *            the datetime which is closest to this value. If this is
     *            <code>null</code>, the position closest to the upper value of
     *            tExtent will be used.
     * @param tExtent
     *            The time range to extract data from. Any features which have
     *            values within this time range (as well as the specified
     *            {@link HorizontalGrid} and time range) will be read.
     * @return The extracted {@link MapFeature}
     * @throws DataReadingException
     *             If the underlying data cannot be read for any reason
     */
    public MapFeature readMapData(Set<String> varIds, HorizontalGrid hGrid, Double targetZ,
            Extent<Double> zExtent, DateTime targetTime, Extent<DateTime> tExtent)
            throws DataReadingException;

    /**
     * Finds the IDs of all features within the specified 4D bounding box.
     * 
     * @param hBbox
     *            The {@link BoundingBox} representing the horizontal extent to
     *            search
     * @param zExtent
     *            The vertical range to search
     * @param tExtent
     *            The time range to search
     * @return A {@link Collection} of feature IDs for features which have data
     *         falling into the given 4D bounding box.
     * @throws DataReadingException
     *             If there is a problem reading the underlying data
     */
    public Collection<String> findFeatureIdsInArea(HorizontalGrid hGrid, Extent<Double> zExtent,
            Extent<DateTime> tExtent) throws DataReadingException;

    /**
     * Extracts a {@link PointSeriesFeature} representing a time series from
     * this {@link GridDataset}
     * 
     * @param varIds
     *            The variables to extract. If this is <code>null</code>, all
     *            available variables (determined by the result of
     *            {@link GridDataset#getVariableIds()}) will be read
     * @param hPos
     *            The {@link HorizontalPosition} at which to extract time series
     *            data
     * @param zPos
     *            The {@link VerticalPosition} at which to extract time series
     *            data. If this is <code>null</code> and the target variable has
     *            a vertical axis, the position closest to the surface will be
     *            read
     * @param timeAxis
     *            The {@link TimeAxis} representing the domain to extract data
     *            on
     * @return The extracted {@link PointSeriesFeature}
     * @throws DataReadingException
     *             If the underlying data cannot be read for any reason
     */
    public PointSeriesFeature readTimeSeriesData(Set<String> varIds, HorizontalPosition hPos,
            VerticalPosition zPos, TimeAxis timeAxis) throws DataReadingException;

    /**
     * Extracts a {@link ProfileFeature} representing a vertical profile from
     * this {@link GridDataset}
     * 
     * @param varIds
     *            The variables to extract. If this is <code>null</code>, all
     *            available variables (determined by the result of
     *            {@link GridDataset#getVariableIds()}) will be read
     * @param hPos
     *            The {@link HorizontalPosition} at which to extract profile
     *            data
     * @param zAxis
     *            The {@link VerticalAxis} on which to extract profile data
     * @param time
     *            The target {@link DateTime}. If this is <code>null</code> and
     *            the target variable has a time axis, the time closest to the
     *            current time
     * @return The extracted {@link ProfileFeature}
     * @throws DataReadingException
     *             If the underlying data cannot be read for any reason
     */
    public ProfileFeature readProfileData(Set<String> varIds, HorizontalPosition hPos,
            VerticalAxis zAxis, DateTime time) throws DataReadingException;

    /**
     * Extracts a {@link TrajectoryFeature} representing an arbitrary path
     * through 4D space from this {@link GridDataset}
     * 
     * @param varIds
     *            The variables to extract. If this is <code>null</code>, all
     *            available variables (determined by the result of
     *            {@link GridDataset#getVariableIds()}) will be read
     * @param domain
     *            The {@link TrajectoryDomain} on which to extract data.
     * @return The extracted {@link TrajectoryFeature}
     * @throws DataReadingException
     *             If the underlying data cannot be read for any reason
     */
    public TrajectoryFeature readTrajectoryData(Set<String> varIds, TrajectoryDomain domain)
            throws DataReadingException;

    /**
     * Reads a single data point from this {@link GridDataset}
     * 
     * @param variableId
     *            The ID of the variable to read
     * @param position
     *            The {@link HorizontalPosition} of the point to read
     * @param zPos
     *            The target z-position. If this is <code>null</code> and the
     *            target variable has a vertical axis, the position closest to
     *            the surface will be read
     * @param time
     *            The target {@link DateTime}. If this is <code>null</code> and
     *            the target variable has a time axis, the time closest to the
     *            current time
     * @return A {@link Number} representing the data value at the specified
     *         point.
     * @throws DataReadingException
     */
    public Number readSinglePoint(String variableId, HorizontalPosition position, Double zPos,
            DateTime time) throws DataReadingException;
}
