import { useEditor, EditorContent } from '@tiptap/react';
import StarterKit from '@tiptap/starter-kit';
import TextStyle from '@tiptap/extension-text-style';
import Color from '@tiptap/extension-color';
import TextAlign from '@tiptap/extension-text-align';
import { 
  Bold, 
  Italic, 
  Strikethrough,
  AlignLeft,
  AlignCenter,
  AlignRight,
  Heading,
  List,
  ListOrdered,
  Palette,
  Quote,
  Smile
} from 'lucide-react';


const RichTextEditor = ({ content, onChange }) => {
  const editor = useEditor({
    extensions: [
      StarterKit.configure({
        bulletList: {
          keepMarks: true,
          keepAttributes: false,
        },
        orderedList: {
          keepMarks: true,
          keepAttributes: false,
        },
      }),
      TextStyle,
      Color,
      TextAlign.configure({
        types: ['heading', 'paragraph'],
      }),
    ],
    content,
    onUpdate: ({ editor }) => {
      onChange(editor.getHTML());
    },
    editorProps: {
      attributes: {
        class: 'prose prose-sm sm:prose lg:prose-lg xl:prose-xl mx-auto focus:outline-none',
      },
    },
  });

  if (!editor) {
    return null;
  }

  const colors = ['#000000', '#FF0000', '#00FF00', '#0000FF', '#FFA500', '#800080'];
  const emojis = ['😀', '👍', '❤️', '🎉', '✨', '🚀', '💡', '⭐', '📝', '✅'];

  const MenuButton = ({ onClick, isActive, icon: Icon, title }) => (
    <button
      onClick={onClick}
      className={`p-2 rounded hover:bg-gray-200 ${isActive ? 'bg-gray-200' : ''}`}
      title={title}
    >
      <Icon className="w-4 h-4" />
    </button>
  );

  return (
    <div className="border rounded-lg overflow-hidden">
      <div className="bg-gray-50 p-2 border-b flex flex-wrap gap-2">
        {/* Text Style */}
        <div className="flex gap-1 border-r pr-2">
          <MenuButton
            onClick={() => editor.chain().focus().toggleBold().run()}
            isActive={editor.isActive('bold')}
            icon={Bold}
            title="Bold"
          />
          <MenuButton
            onClick={() => editor.chain().focus().toggleItalic().run()}
            isActive={editor.isActive('italic')}
            icon={Italic}
            title="Italic"
          />
          <MenuButton
            onClick={() => editor.chain().focus().toggleStrike().run()}
            isActive={editor.isActive('strike')}
            icon={Strikethrough}
            title="Strikethrough"
          />
        </div>

        {/* Lists */}
        <div className="flex gap-1 border-r pr-2">
          <MenuButton
            onClick={() => editor.chain().focus().toggleBulletList().run()}
            isActive={editor.isActive('bulletList')}
            icon={List}
            title="Bullet List"
          />
          <MenuButton
            onClick={() => editor.chain().focus().toggleOrderedList().run()}
            isActive={editor.isActive('orderedList')}
            icon={ListOrdered}
            title="Numbered List"
          />
        </div>

        {/* Alignment */}
        <div className="flex gap-1 border-r pr-2">
          <MenuButton
            onClick={() => editor.chain().focus().setTextAlign('left').run()}
            isActive={editor.isActive({ textAlign: 'left' })}
            icon={AlignLeft}
            title="Align Left"
          />
          <MenuButton
            onClick={() => editor.chain().focus().setTextAlign('center').run()}
            isActive={editor.isActive({ textAlign: 'center' })}
            icon={AlignCenter}
            title="Align Center"
          />
          <MenuButton
            onClick={() => editor.chain().focus().setTextAlign('right').run()}
            isActive={editor.isActive({ textAlign: 'right' })}
            icon={AlignRight}
            title="Align Right"
          />
        </div>

        {/* Blockquote */}
        <MenuButton
          onClick={() => editor.chain().focus().toggleBlockquote().run()}
          isActive={editor.isActive('blockquote')}
          icon={Quote}
          title="Quote"
        />

        {/* Heading */}
        <div className="relative group">
          <button className="p-2 rounded hover:bg-gray-200" title="Heading">
            <Heading className="w-4 h-4" />
          </button>
          <div className="absolute hidden group-hover:block bg-white shadow-lg rounded-lg p-2 z-10">
            {[1, 2, 3].map((level) => (
              <button
                key={level}
                onClick={() => editor.chain().focus().toggleHeading({ level }).run()}
                className={`block w-full px-4 py-2 text-left hover:bg-gray-100 ${
                  editor.isActive('heading', { level }) ? 'bg-gray-200' : ''
                }`}
              >
                Heading {level}
              </button>
            ))}
          </div>
        </div>

        {/* Colors */}
        <div className="relative group">
          <button className="p-2 rounded hover:bg-gray-200" title="Text Color">
            <Palette className="w-4 h-4" />
          </button>
          <div className="absolute hidden group-hover:flex bg-white shadow-lg rounded-lg p-2 gap-1 z-10">
            {colors.map((color) => (
              <button
                key={color}
                onClick={() => editor.chain().focus().setColor(color).run()}
                className="w-6 h-6 rounded"
                style={{ backgroundColor: color }}
                title={color}
              />
            ))}
          </div>
        </div>

        {/* Emojis */}
        <div className="relative group">
          <button className="p-2 rounded hover:bg-gray-200" title="Emojis">
            <Smile className="w-4 h-4" />
          </button>
          <div className="absolute hidden group-hover:grid grid-cols-5 bg-white shadow-lg rounded-lg p-2 gap-1 z-10">
            {emojis.map((emoji) => (
              <button
                key={emoji}
                onClick={() => editor.chain().focus().insertContent(emoji).run()}
                className="w-8 h-8 flex items-center justify-center hover:bg-gray-100 rounded"
                title="Insert Emoji"
              >
                {emoji}
              </button>
            ))}
          </div>
        </div>
      </div>

      <EditorContent 
        editor={editor} 
        className="prose max-w-none p-4 [&>ul]:list-disc [&>ul]:pl-4 [&>ol]:list-decimal [&>ol]:pl-4" 
      />
    </div>
  );
};

export default RichTextEditor;