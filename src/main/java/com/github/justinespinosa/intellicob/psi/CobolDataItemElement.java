package com.github.justinespinosa.intellicob.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.jetbrains.annotations.NotNull;

import static com.github.justinespinosa.intellicob.psi.PsiUtil.createDataItemName;

public class CobolDataItemElement extends ASTWrapperPsiElement implements PsiNamedElement {
    public CobolDataItemElement(@NotNull ASTNode node) {
        super(node);
    }

    public CobolDataItemElement getParentItem() {
        PsiElement parent = getParent();
        if (parent instanceof CobolDataItemElement) {
            return (CobolDataItemElement) parent;
        }
        return null;
    }

    public boolean hasParentItem() {
        return getParentItem() != null;
    }

    @Override
    public String getName() {
        ASTNode keyNode = getNode().findChildByType(CobolTypes.DATA_ITEM_NAME_);
        if (keyNode == null) {
            return "";
        }
        return keyNode.getText();
    }

    @Override
    public PsiElement setName(String newName) {
        ASTNode keyNode = getNode().findChildByType(CobolTypes.DATA_ITEM_NAME_);
        ASTNode newKeyNode = createDataItemName(getProject(), newName);
        getNode().replaceChild(keyNode, newKeyNode);
        return this;
    }
}
