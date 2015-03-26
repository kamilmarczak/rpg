package com.rpg.game.entities.creature.npc;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.rpg.game.entities.creature.Npc;
import com.rpg.game.entities.creature.Player;
import com.rpg.game.quests.QuestManager;

public class RegularNpc extends Npc {
	private QuestManager questManager;
	private Stage stage;
	private String[] questList = { "QuestOne", "QuestTwo" };

	public RegularNpc(float x, float y, World world, Player player,QuestManager questManager, Stage stage) {
		super(x, y, world, player);
		this.questManager = questManager;
		this.stage = stage;

	}

	@Override
	public void showDialog() {
		questManager.quest(questList);
		dialog = new Dialog("", windowStyle);
		dialog.align(Align.center);
		dialog.text(new Label(questManager.getQuestDialog(), labelStyle));
		
		
		dialog.button(buttonOK).setSize(buttonOK.getWidth(),buttonOK.getHeight());
		dialog.button(buttonCancel).setSize(buttonCancel.getWidth(),buttonCancel.getHeight());
		dialog.pack();
		dialog.setPosition(stage.getWidth() / 2f - dialog.getWidth() / 2,stage.getHeight() / 2f - dialog.getHeight() / 2);
		dialog.debug();
		stage.addActor(dialog);

	}

}
