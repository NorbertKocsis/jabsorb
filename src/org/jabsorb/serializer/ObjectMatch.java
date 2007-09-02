/*
 * jabsorb - a Java to JavaScript Advanced Object Request Broker
 * http://www.jabsorb.org
 *
 * Copyright 2007 Arthur Blake and William Becker
 *
 * based on original code from
 * JSON-RPC-Java - a JSON-RPC to Java Bridge with dynamic invocation
 *
 * Copyright Metaparadigm Pte. Ltd. 2004.
 * Michael Clark <michael@metaparadigm.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.jabsorb.serializer;

/**
 * <p>
 * This class is returned from the Serializer tryUnmarshall method to indicate
 * number of mismatched fields. This is used to handle ambiguities with
 * JavaScript's typeless objects combined with and Java's operator overloading.
 * </p>
 * TODO: wouldn't a better name for this class be ObjectMismatch as it's would
 * TODO: be more descriptive. The name ObjectMatch is a little confusing TODO:
 * because it implies the opposite of what the class actually stores TODO:
 * (ObjectMismatch) either that, or I'm not understanding something TODO:
 * correctly... [WB: I agree!]
 */
public class ObjectMatch
{

  /**
   * The objects match
   */
  public final static ObjectMatch OKAY = new ObjectMatch(-1);

  /**
   * The objects do not match (?)
   */
  public final static ObjectMatch NULL = new ObjectMatch(0);

  /**
   * The number of mismatched fields that occured on a tryUnmarshall call.
   */
  private int mismatch;

  /**
   * Create a new ObjectMatch object with the given number of mismatches.
   * 
   * @param mismatch
   *          the number of mismatched fields that occured on a tryUnmarshall
   *          call.
   */
  public ObjectMatch(int mismatch)
  {
    this.mismatch = mismatch;
  }

  /**
   * Get the number of mismatched fields that occured on a tryUnmarshall call.
   * 
   * @return the number of mismatched fields that occured on a tryUnmarshall
   *         call.
   */
  public int getMismatch()
  {
    return mismatch;
  }

  /**
   * Compare another ObjectMatch with this ObjectMatch and return the one that
   * has the most mismatches.
   * 
   * @param m
   *          ObjectMatch to compare this ObjectMatch to.
   * 
   * @return this ObjectMatch if it has more mismatches, else the passed in
   *         ObjectMatch.
   */
  public ObjectMatch max(ObjectMatch m)
  {
    if (this.mismatch > m.mismatch)
    {
      return this;
    }
    return m;
  }
}