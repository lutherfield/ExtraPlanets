package com.mjr.extraplanets.nei.machines;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.nei.NEIExtraPlanetsConfig;

public class CrystallizerRecipeHandler extends TemplateRecipeHandler {
	private static final ResourceLocation crystallizerGuiTexture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/crystallizer.png");
	int ticksPassed;

	public String getRecipeId() {
		return "extraplanets.crystallizer";
	}

	@Override
	public int recipiesPerPage() {
		return 2;
	}

	public Set<Entry<PositionedStack, PositionedStack>> getRecipes() {
		return NEIExtraPlanetsConfig.getCrystallizerRecipes();
	}

	@Override
	public void drawBackground(int recipe) {
		int progress = this.ticksPassed % 144;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiDraw.changeTexture(CrystallizerRecipeHandler.crystallizerGuiTexture);
		GuiDraw.drawTexturedModalRect(-2, 0, 3, 4, 168, 64);
		if (progress < 104 && progress > 40) {
			GuiDraw.drawTexturedModalRect(2, 42, 176 + 16, 6, 16, 20);
		}
		GuiDraw.drawTexturedModalRect(21, 21, 0, 186, progress, 20);
	}

	@Override
	public void onUpdate() {
		this.ticksPassed += 2;
		super.onUpdate();
	}

	@Override
	public void loadTransferRects() {
	}

	@Override
	public void loadCraftingRecipes(String outputId, Object... results) {
		if (outputId.equals(this.getRecipeId())) {
			for (final Map.Entry<PositionedStack, PositionedStack> irecipe : this.getRecipes()) {
				this.arecipes.add(new CachedCrystallizerRecipe(irecipe));
			}
		} else {
			super.loadCraftingRecipes(outputId, results);
		}
	}

	@Override
	public void loadCraftingRecipes(ItemStack result) {
		for (final Map.Entry<PositionedStack, PositionedStack> irecipe : this.getRecipes()) {
			if (NEIServerUtils.areStacksSameTypeCrafting(irecipe.getValue().item, result)) {
				this.arecipes.add(new CachedCrystallizerRecipe(irecipe));
			}
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient) {
		for (final Map.Entry<PositionedStack, PositionedStack> irecipe : this.getRecipes()) {
			if (NEIServerUtils.areStacksSameTypeCrafting(ingredient, irecipe.getKey().item)) {
				this.arecipes.add(new CachedCrystallizerRecipe(irecipe));
				break;
			}
		}
	}

	@Override
	public ArrayList<PositionedStack> getIngredientStacks(int recipe) {
		if (this.ticksPassed % 144 > 20) {
			ArrayList<PositionedStack> stacks = new ArrayList<PositionedStack>();
			stacks.add(new PositionedStack(new ItemStack(Items.bucket, 1, 0), this.arecipes.get(recipe).getIngredients().get(0).relx, this.arecipes.get(recipe).getIngredients().get(0).rely));
			return stacks;
		} else {
			return (ArrayList<PositionedStack>) this.arecipes.get(recipe).getIngredients();
		}
	}

	@Override
	public PositionedStack getResultStack(int recipe) {
		return this.arecipes.get(recipe).getResult();
	}

	public class CachedCrystallizerRecipe extends TemplateRecipeHandler.CachedRecipe {
		public PositionedStack input;
		public PositionedStack output;

		@Override
		public PositionedStack getIngredient() {
			return this.input;
		}

		@Override
		public PositionedStack getResult() {
			return this.output;
		}

		public CachedCrystallizerRecipe(PositionedStack pstack1, PositionedStack pstack2) {
			super();
			this.input = pstack1;
			this.output = pstack2;
		}

		public CachedCrystallizerRecipe(Map.Entry<PositionedStack, PositionedStack> recipe) {
			this(recipe.getKey(), recipe.getValue());
		}
	}

	@Override
	public String getRecipeName() {
		return GCCoreUtil.translate("tile.basicCrystallizer.name");
	}

	@Override
	public String getGuiTexture() {
		return Constants.TEXTURE_PREFIX + "textures/gui/crystallizer.png";
	}

	@Override
	public void drawForeground(int recipe) {
	}
}