/*
 * Copyright (c) 2014, Regents of the University of California
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package edu.uci.python.nodes.attribute;

import com.oracle.truffle.api.nodes.*;

import edu.uci.python.runtime.object.*;

public abstract class AttributeWriteNode extends Node {

    public static AttributeWriteNode create(StorageLocation location) {
        if (location instanceof ObjectStorageLocation) {
            return new WriteObjectAttributeNode((ObjectStorageLocation) location);
        } else if (location instanceof IntStorageLocation) {
            return new WriteIntAttributeNode((IntStorageLocation) location);
        } else if (location instanceof FloatStorageLocation) {
            return new WriteDoubleAttributeNode((FloatStorageLocation) location);
        }

        // TODO: write boolean
        throw new IllegalStateException();
    }

    public abstract void setValueUnsafe(PythonBasicObject storage, Object value) throws GeneralizeStorageLocationException;

    public void setIntValueUnsafe(PythonBasicObject storage, int value) throws GeneralizeStorageLocationException {
        setValueUnsafe(storage, value);
    }

    public void setDoubleValueUnsafe(PythonBasicObject storage, double value) throws GeneralizeStorageLocationException {
        setValueUnsafe(storage, value);
    }

    public void setBooleanValueUnsafe(PythonBasicObject storage, boolean value) throws GeneralizeStorageLocationException {
        setValueUnsafe(storage, value);
    }

    public static final class WriteObjectAttributeNode extends AttributeWriteNode {

        private final ObjectStorageLocation objLocation;

        public WriteObjectAttributeNode(ObjectStorageLocation objLocation) {
            this.objLocation = objLocation;
        }

        @Override
        public void setValueUnsafe(PythonBasicObject storage, Object value) {
            objLocation.write(storage, value);
        }
    }

    public static final class WriteIntAttributeNode extends AttributeWriteNode {

        private final IntStorageLocation intLocation;

        public WriteIntAttributeNode(IntStorageLocation intLocation) {
            this.intLocation = intLocation;
        }

        @Override
        public void setValueUnsafe(PythonBasicObject storage, Object value) throws GeneralizeStorageLocationException {
            intLocation.write(storage, value);
        }

        @Override
        public void setIntValueUnsafe(PythonBasicObject storage, int value) throws GeneralizeStorageLocationException {
            intLocation.write(storage, value);
        }
    }

    public static final class WriteDoubleAttributeNode extends AttributeWriteNode {

        private final FloatStorageLocation floatLocation;

        public WriteDoubleAttributeNode(FloatStorageLocation floatLocation) {
            this.floatLocation = floatLocation;
        }

        @Override
        public void setValueUnsafe(PythonBasicObject storage, Object value) throws GeneralizeStorageLocationException {
            floatLocation.write(storage, value);
        }

        @Override
        public void setDoubleValueUnsafe(PythonBasicObject storage, double value) throws GeneralizeStorageLocationException {
            floatLocation.write(storage, value);
        }
    }

    public static final class WriteBooleanAttributeNode extends AttributeWriteNode {

        private final IntStorageLocation intLocation;

        public WriteBooleanAttributeNode(IntStorageLocation intLocation) {
            this.intLocation = intLocation;
        }

        @Override
        public void setValueUnsafe(PythonBasicObject storage, Object value) throws GeneralizeStorageLocationException {
            intLocation.write(storage, value);
        }

        @Override
        public void setBooleanValueUnsafe(PythonBasicObject storage, boolean value) throws GeneralizeStorageLocationException {
            intLocation.write(storage, value);
        }
    }

}
