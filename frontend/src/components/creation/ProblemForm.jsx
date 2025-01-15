import React from 'react';
import { useForm, useFieldArray } from 'react-hook-form';
import { Plus, Upload, ChevronUp, ChevronDown } from 'lucide-react';
import { toast } from 'react-hot-toast';
import { createProblem } from '../../services/api';
import TextEditor from './TextEditor';

export default function ProblemForm({ contestId }) {
  const { register, control, handleSubmit, formState: { errors }, watch, setValue } = useForm({
    defaultValues: {
      problemType: '',
      contentItems: [{ type: 'text', content: '' }]
    }
  });

  const { fields, append, remove, move } = useFieldArray({
    control,
    name: 'contentItems'
  });

  const onSubmit = async (data) => {
    try {
      const formData = new FormData();
      formData.append('problemType', data.problemType);
      
      data.contentItems.forEach((item, index) => {
        formData.append(`contentDtos[${index}].contentOrder`, index.toString());
        if (item.type === 'file' && item.content[0]) {
          formData.append(`contentDtos[${index}].content`, item.content[0]);
        } else if (item.type === 'text' && item.content) {
          const blob = new Blob([item.content], { type: 'text/html' });
          formData.append(`contentDtos[${index}].content`, blob, 'content.html');
        }
      });

      const response = await createProblem(contestId, formData);
      if (response.success) {
        toast.success('Problem created successfully!');
      } else {
        toast.error(response.message || 'Failed to create problem');
      }
    } catch (error) {
      // Safe error handling without logging the entire error object
      const errorMessage = error instanceof Error ? error.message : 'Failed to create problem';
      toast.error(errorMessage);
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)} className="max-w-4xl mx-auto p-6 space-y-6">
      <div className="space-y-4">
        <h2 className="text-2xl font-bold text-gray-900">Create New Problem</h2>
        
        <div>
          <label className="block text-sm font-medium text-gray-700">Problem Type</label>
          <input
            type="text"
            {...register('problemType', { required: 'Problem type is required' })}
            className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm"
          />
          {errors.problemType && (
            <p className="mt-1 text-sm text-red-600">{errors.problemType.message}</p>
          )}
        </div>

        <div className="space-y-4">
          <div className="flex items-center justify-between">
            <h3 className="text-lg font-medium text-gray-900">Content</h3>
            <div className="space-x-2">
              <button
                type="button"
                onClick={() => append({ type: 'text', content: '' })}
                className="inline-flex items-center px-3 py-1 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700"
              >
                <Plus className="h-4 w-4 mr-1" />
                Add Text
              </button>
              <button
                type="button"
                onClick={() => append({ type: 'file', content: undefined })}
                className="inline-flex items-center px-3 py-1 border border-transparent text-sm font-medium rounded-md text-white bg-green-600 hover:bg-green-700"
              >
                <Plus className="h-4 w-4 mr-1" />
                Add File
              </button>
            </div>
          </div>

          {fields.map((field, index) => (
            <div key={field.id} className="relative bg-white p-4 rounded-lg border">
              <div className="absolute right-2 top-2 flex gap-1">
                {index > 0 && (
                  <button
                    type="button"
                    onClick={() => move(index, index - 1)}
                    className="p-1 text-gray-500 hover:text-gray-700"
                  >
                    <ChevronUp className="h-5 w-5" />
                  </button>
                )}
                {index < fields.length - 1 && (
                  <button
                    type="button"
                    onClick={() => move(index, index + 1)}
                    className="p-1 text-gray-500 hover:text-gray-700"
                  >
                    <ChevronDown className="h-5 w-5" />
                  </button>
                )}
              </div>

              {field.type === 'text' ? (
                <div className="mt-4">
                  <TextEditor
                    content={watch(`contentItems.${index}.content`) || ''}
                    onChange={(content) => setValue(`contentItems.${index}.content`, content)}
                  />
                </div>
              ) : (
                <div className="mt-4">
                  <label className="w-full flex items-center justify-center px-6 py-3 border-2 border-gray-300 border-dashed rounded-md hover:border-indigo-500 cursor-pointer">
                    <input
                      type="file"
                      {...register(`contentItems.${index}.content` , {
                        required: 'File is required'
                      })}
                      className="sr-only"
                    />
                    <div className="space-y-1 text-center">
                      <Upload className="mx-auto h-8 w-8 text-gray-400" />
                      <div className="flex text-sm text-gray-600">
                        <span>Upload a file</span>
                      </div>
                    </div>
                  </label>
                </div>
              )}
            </div>
          ))}
        </div>

        <div className="pt-4">
          <button
            type="submit"
            className="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
          >
            Create Problem
          </button>
        </div>
      </div>
    </form>
  );
}