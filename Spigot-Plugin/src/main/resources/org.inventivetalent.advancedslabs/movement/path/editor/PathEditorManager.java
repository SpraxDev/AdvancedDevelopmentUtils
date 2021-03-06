/*
 * Copyright 2015-2016 inventivetalent. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ''AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and contributors and should not be interpreted as representing official policies,
 *  either expressed or implied, of anybody else.
 */
package org.inventivetalent.advancedslabs.movement.path.editor;

import org.bukkit.Location;
import org.inventivetalent.advancedslabs.AdvancedSlabs;
import org.inventivetalent.advancedslabs.api.path.IPathPoint;
import org.inventivetalent.advancedslabs.api.path.ISlabPath;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PathEditorManager {

    private AdvancedSlabs plugin;
    public final Map<UUID, PathEditor> editorMap = new HashMap<>();

    public PathEditorManager(AdvancedSlabs plugin) {
        this.plugin = plugin;
    }

    public PathEditor getEditor(UUID uuid) {
        return editorMap.get(uuid);
    }

    public PathEditor getEditorForPathBlock(Location location) {
        for (PathEditor editor : editorMap.values()) {
            if (editor.path == null) {
                continue;
            }
            for (IPathPoint point : editor.path.getPoints()) {
                if (point.isAt(location)) {
                    return editor;
                }
            }
        }
        return null;
    }

    public PathEditor newEditor(UUID uuid, ISlabPath path) {
        removeEditor(uuid);

        PathEditor editor = new PathEditor();
        editor.player = uuid;
        editor.path = path;
        editorMap.put(uuid, editor);

        return editor;
    }

    public void removeEditor(UUID uuid) {
        PathEditor editor = editorMap.remove(uuid);
    }

    public boolean isEditing(UUID uuid) {
        return editorMap.containsKey(uuid);
    }
}
