package com.tanerdiler.web.component.tinymce;

import wicket.contrib.tinymce.settings.Button;
import wicket.contrib.tinymce.settings.ContextMenuPlugin;
import wicket.contrib.tinymce.settings.DateTimePlugin;
import wicket.contrib.tinymce.settings.EmotionsPlugin;
import wicket.contrib.tinymce.settings.FullScreenPlugin;
import wicket.contrib.tinymce.settings.IESpellPlugin;
import wicket.contrib.tinymce.settings.MediaPlugin;
import wicket.contrib.tinymce.settings.PastePlugin;
import wicket.contrib.tinymce.settings.PreviewPlugin;
import wicket.contrib.tinymce.settings.PrintPlugin;
import wicket.contrib.tinymce.settings.SearchReplacePlugin;
import wicket.contrib.tinymce.settings.SpellCheckPlugin;
import wicket.contrib.tinymce.settings.TablePlugin;
import wicket.contrib.tinymce.settings.TinyMCESettings;

public class SettingManager {
	public static TinyMCESettings getFullConf(){
        TinyMCESettings settings = new TinyMCESettings(TinyMCESettings.Theme.advanced);

        ContextMenuPlugin contextMenuPlugin = new ContextMenuPlugin();
        settings.register(contextMenuPlugin);
        // first toolbar
        // second toolbar
        PastePlugin pastePlugin = new PastePlugin();
        SearchReplacePlugin searchReplacePlugin = new SearchReplacePlugin();
        DateTimePlugin dateTimePlugin = new DateTimePlugin();
        dateTimePlugin.setDateFormat("Date: %m-%d-%Y");
        dateTimePlugin.setTimeFormat("Time: %H:%M");
        PreviewPlugin previewPlugin = new PreviewPlugin();


        // third toolbar
        TablePlugin tablePlugin = new TablePlugin();
        EmotionsPlugin emotionsPlugin = new EmotionsPlugin();
        IESpellPlugin iespellPlugin = new IESpellPlugin();
        MediaPlugin mediaPlugin = new MediaPlugin();
        PrintPlugin printPlugin = new PrintPlugin();
        FullScreenPlugin fullScreenPlugin = new FullScreenPlugin();
        settings.add(Button.forecolor, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
        settings.add(Button.backcolor, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
        settings.add(Button.separator, TinyMCESettings.Toolbar.second, TinyMCESettings.Position.before);
        settings.add(emotionsPlugin.getEmotionsButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
        settings.add(iespellPlugin.getIespellButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
        settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
        settings.add(printPlugin.getPrintButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
        settings.add(Button.separator, TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
         settings.add(fullScreenPlugin.getFullscreenButton(), TinyMCESettings.Toolbar.third,
                TinyMCESettings.Position.after);
         settings.add(previewPlugin.getPreviewButton(), TinyMCESettings.Toolbar.third, TinyMCESettings.Position.after);
         
        // fourth toolbar
        SpellCheckPlugin spellCheckPlugin = new SpellCheckPlugin();
        settings.add(tablePlugin.getTableControls(), TinyMCESettings.Toolbar.fourth, TinyMCESettings.Position.before);
        // other settings
        settings.setToolbarAlign(TinyMCESettings.Align.left);
        settings.setToolbarLocation(TinyMCESettings.Location.top);
        settings.setStatusbarLocation(TinyMCESettings.Location.bottom);
        //settings.setResizing(true);
        //settings.setAutoResize(true);
        settings.addCustomSetting("height: \"400px\"");
        settings.addCustomSetting("width: \"600px\"");
//        settings.addCustomSetting("forced_root_block: \"false\"");
//        settings.addCustomSetting("force_p_newlines: \"false\"");
//        settings.addCustomSetting("remove_linebreaks: \"false\"");
//        settings.addCustomSetting("force_br_newlines: \"true\"");
//        settings.addCustomSetting("remove_trailing_nbsp: \"false\"");
//        settings.addCustomSetting("verify_html: \"false\"");
        return settings;
	}
	
}
