/*
 * Druid - a distributed column store.
 * Copyright (C) 2012, 2013  Metamarkets Group Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package io.druid.segment;

import io.druid.segment.data.IndexedInts;
import io.druid.segment.data.Offset;

/**
*/
class IndexedIntsOffset implements Offset
{
  int currRow;
  private final IndexedInts invertedIndex;

  public IndexedIntsOffset(IndexedInts invertedIndex)
  {
    this.invertedIndex = invertedIndex;
    currRow = 0;
  }

  @Override
  public void increment()
  {
    ++currRow;
  }

  @Override
  public boolean withinBounds()
  {
    return currRow < invertedIndex.size();
  }

  @Override
  public Offset clone()
  {
    final IndexedIntsOffset retVal = new IndexedIntsOffset(invertedIndex);
    retVal.currRow = currRow;
    return retVal;
  }

  @Override
  public int getOffset()
  {
    return invertedIndex.get(currRow);
  }
}
